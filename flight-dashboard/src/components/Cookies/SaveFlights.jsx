import Cookies from 'js-cookie'


export default function saveFlightList (trip) {
    // have to save all of the segments
    let trips = [];
    trip.trips.map((item) => {
        if (item && item.segments) {
            let values = { segments: [] };
            item.segments.map((segment) => {
                const { airlineIATA, flightNumber, departureDate } = segment;
                values.segments.push({ airlineIATA, flightNumber, departureDate });
            });
            trips.push(values);
        }
    });
    Cookies.set('trips', JSON.stringify(trips));
}


export function getTripsCookie () {
    let out = undefined;
    try {
        out = {trips: JSON.parse(Cookies.get('trips'))}; 
    } catch (syntaxError) {
        console.error('Couldn\' parse trips cookie!');
    }
    return out; 
}

export function clearTripsCookie () {
    Cookies.set('trips', JSON.stringify( {trips: [] } ));
}