import * as React from "react";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import airlineCodes from "./airlineCodes";

export default function AirlineInput({ selectedAirline, setSelectedAirline }) {
  const handleAirlineChange = (event, value) => {
    setSelectedAirline((prevAirlines) => [...prevAirlines, value.iataCode]);
  };
  return (
    <Autocomplete
      disablePortal
      size="small"
      options={airlineCodes}
      sx={{ width: 300 }}
      getOptionLabel={(option) => `${option.airlineName} (${option.iataCode})`}
      renderInput={(params) => <TextField {...params} label="Airline" />}
      onChange={handleAirlineChange}
    />
  );
}
