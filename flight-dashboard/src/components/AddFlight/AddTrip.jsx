import { Button } from "@mui/material";
import AddFlightList from "./AddFlight";
import { useState } from "react";

export default function AddTrip({ isAddFlightOpen, closeAddFlight, setTrips, setSegments }) {
  /* State to hold temp values */
  const [selectedAirline, setSelectedAirline] = useState([]);
  const [selectedFlightNumber, setSelectedFlightNumber] = useState([]);
  const [selectedDate, setSelectedDate] = useState([]);

  const handleAddTripClick = () => {
    const newFlights = selectedAirline.map((airline, index) => ({
      airlineIATA: airline,
      flightNumber: selectedFlightNumber[index],
      departureDate: selectedDate[index],
    }));
    closeAddFlight();
    setSelectedAirline([]);
    setSelectedFlightNumber([]);
    setSelectedDate([]);

    setTrips((prevState) => {
      if (prevState && Array.isArray(prevState.trips)) {
        return {
          trips: [...prevState.trips, { segments: [...newFlights] }],
        }
      }
      // if prevstate doesn't exist we just populate with new segment data
      return {
        trips: [{ segments: [...newFlights] }],
      }
      });
  };
  return (
    <>
      {isAddFlightOpen && (
        <div className="fixed top-0 left-0 z-10 bg-gray-900/50 w-screen h-screen">
          <div
            id="addFlightDialog"
            className="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 bg-gray-50 rounded-sm p-4"
          >
            <h1 className="font-semibold text-lg text-center">Add a trip</h1>
            <div className="flex flex-col gap-4 p-4">
              <AddFlightList
                selectedAirline={selectedAirline}
                setSelectedAirline={setSelectedAirline}
                selectedFlightNumber={selectedFlightNumber}
                setSelectedFlightNumber={setSelectedFlightNumber}
                selectedDate={selectedDate}
                setSelectedDate={setSelectedDate}
              />
              <div className="flex justify-center gap-4">
                <Button variant="outlined" onClick={closeAddFlight}>
                  Close
                </Button>
                <Button variant="contained" onClick={handleAddTripClick}>
                  Add trip
                </Button>
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
