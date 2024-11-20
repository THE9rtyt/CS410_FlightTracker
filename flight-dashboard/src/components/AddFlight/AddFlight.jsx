import { useState } from "react";
import {
  Input,
  Button,
  InputAdornment,
  OutlinedInput,
  InputLabel,
  FormControl,
} from "@mui/material";
import AirlineInput from "./AirlineInput";

export default function AddFlightList({
  selectedAirline,
  setSelectedAirline,
  selectedFlightNumber,
  setSelectedFlightNumber,
  selectedDate,
  setSelectedDate,
}) {
  // Initialize state with one instance of AddFlight
  const [flights, setFlights] = useState([
    <AddFlight
      key={0}
      selectedAirline={selectedAirline}
      setSelectedAirline={setSelectedAirline}
      selectedFlightNumber={selectedFlightNumber}
      setSelectedFlightNumber={setSelectedFlightNumber}
      selectedDate={selectedDate}
      setSelectedDate={setSelectedDate}
    />,
  ]);

  const addFlight = () => {
    setFlights((prevFlights) => [
      ...prevFlights,
      <AddFlight
        key={prevFlights.length}
        selectedAirline={selectedAirline}
        setSelectedAirline={setSelectedAirline}
        selectedFlightNumber={selectedFlightNumber}
        setSelectedFlightNumber={setSelectedFlightNumber}
        selectedDate={selectedDate}
        setSelectedDate={setSelectedDate}
      />,
    ]);
  };

  return (
    <div className="flex flex-col gap-8 items-center">
      {/* Render all AddFlight components */}
      {flights.map((flight, index) => (
        <div key={index}>{flight}</div>
      ))}
      <Button variant="text" sx={{ width: 128 }} onClick={addFlight}>
        Add segment
      </Button>
    </div>
  );
}

function AddFlight({
  selectedAirline,
  setSelectedAirline,
  setSelectedFlightNumber,
  setSelectedDate,
}) {
  /* State for this segment */
  const handleFlightNumberChange = (event) => {
    setSelectedFlightNumber((prevFlightNumbers) => [...prevFlightNumbers, event.target.value]);
  };
  const handleDateChange = (event) => {
    setSelectedDate((prevDates) => [...prevDates, event.target.value]);
  };
  return (
    <div className="flex flex-col gap-4">
      <AirlineInput selectedAirline={selectedAirline} setSelectedAirline={setSelectedAirline} />
      <FormControl fullWidth size="small">
        <InputLabel htmlFor="flight-number-input">Flight number</InputLabel>
        <OutlinedInput
          id="flight-number-input"
          startAdornment={<InputAdornment position="start">#</InputAdornment>}
          label="Flight number"
          onBlur={handleFlightNumberChange}
        />
      </FormControl>
      <FormControl fullWidth size="small">
        <InputLabel htmlFor="flight-date-input">Date</InputLabel>
        <OutlinedInput
          id="flight-date-input"
          type="date"
          startAdornment={<InputAdornment position="start"></InputAdornment>}
          label="Date"
          onChange={handleDateChange}
        />
      </FormControl>
    </div>
  );
}
