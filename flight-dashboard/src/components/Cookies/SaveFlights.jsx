import trip from "../AddFlight/trip";
import airlineCodes from "../AddFlight/airlineCodes";
import Cookies from 'js-cookie'


export default function saveFlightList () {
    // have to save all of the segments
    let trips = [];
    trip.map((item) => {
        item.trips.map((details, tripIndex) => {
            let values = [];
            details.segments.map((segment) => {
                const code = airlineCodes.find((element) => {
                    return segment.airline === element.airlineName;
                });
                if (code) {
                    const { iataCode } = code;
                    const { flightNumber, dateTime } = segment;
                    values.push({ iataCode, flightNumber, dateTime });
                }
            });
            trips.push(values);
            
        });
    });
    Cookies.set('trips', JSON.stringify(trips));
}


export function getTripsCookie () {
    return JSON.parse(Cookies.get('trips'));
}