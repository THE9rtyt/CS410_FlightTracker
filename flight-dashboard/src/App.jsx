import { useState } from "react";
import Navigation from "./components/Navigation/Navigation";
import { ThemeProvider, createTheme } from "@mui/material";
import BaseCard from "./components/FlightCard/BaseCard";
import AddTrip from "./components/AddFlight/AddTrip";
import trip from "./components/AddFlight/trip";
import saveFlightList, { getTripsCookie } from "./components/Cookies/SaveFlights";
import React from 'react'

function App() {
  /* State to control AddFlight popover */
  const [isAddFlightOpen, setIsAddFlightOpen] = useState(false);
  const openAddFlight = () => setIsAddFlightOpen(true);
  const closeAddFlight = () => setIsAddFlightOpen(false);

  React.useEffect(() => {
    saveFlightList();
    console.log(getTripsCookie());
  }, []);

  const theme = createTheme({
    palette: {
      primary: {
        light: "#757ce8",
        main: "#4338ca", // TailwindCSS Indigo-700
        dark: "#312e81", // TailwindCSS Indigo-900
        contrastText: "#fff",
      },
      secondary: {
        light: "#ff7961",
        main: "#f44336",
        dark: "#ba000d",
        contrastText: "#000",
      },
    },
  });

  return (
    <>
      <ThemeProvider theme={theme}>
        <div className="flex flex-col items-center h-screen w-screen flex-grow">
          <div className="w-full border-b-2">
            <Navigation openAddFlight={openAddFlight} />
          </div>
          <h1 className="font-bold text-4xl mt-6 text-indigo-900">Upcoming trips</h1>
          <div className="flex flex-col md:flex-row mt-8 max-w-xs md:max-w-7xl gap-12">
            {trip.map((tripItem, index) =>
              tripItem.trips.map((tripDetails, tripIndex) => (
                <BaseCard
                  key={tripIndex}
                  tripDetails={tripDetails}
                  segments={tripDetails.segments}
                />
              ))
            )}
          </div>
          <AddTrip isAddFlightOpen={isAddFlightOpen} closeAddFlight={closeAddFlight} />
        </div>
      </ThemeProvider>
    </>
  );
}

export default App;
