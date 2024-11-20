import { useState, useEffect } from "react";
import Navigation from "./components/Navigation/Navigation";
import { ThemeProvider, createTheme } from "@mui/material";
import BaseCard from "./components/FlightCard/BaseCard";
import AddTrip from "./components/AddFlight/AddTrip";
import trip from "./components/AddFlight/trip";

function App() {
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

  /* State to control AddFlight popover */
  const [isAddFlightOpen, setIsAddFlightOpen] = useState(false);
  const openAddFlight = () => setIsAddFlightOpen(true);
  const closeAddFlight = () => setIsAddFlightOpen(false);

  /* State for trips */
  const [trips, setTrips] = useState({ trips: [] });
  const [fetchedData, setFetchedData] = useState(null);

  // Function to fetch data from the API
  useEffect(() => {
    const fetchFlightData = async () => {
      try {
        const response = await fetch("http://localhost:8080/resources/trips", {
          headers: {
            "Content-Type": "application/json",
          },
          method: "POST",
          body: JSON.stringify({ trips: trips.trips }),
        });
        const data = await response.json();
        setFetchedData(data);
      } catch (error) {
        console.error("Error fetching flight data:", error);
      }
    };
    if (
      trips &&
      Array.isArray(trips.trips) &&
      trips.trips.some((trip) => Array.isArray(trip.segments) && trip.segments.length > 0)
    ) {
      fetchFlightData();
    }
  }, [trips]);

  return (
    <>
      <ThemeProvider theme={theme}>
        <div className="flex flex-col items-center h-screen w-screen flex-grow">
          <div className="w-full border-b-2">
            <Navigation openAddFlight={openAddFlight} />
          </div>
          <h1 className="font-bold text-4xl mt-6 text-indigo-900">Upcoming trips</h1>
          <div className="flex flex-col md:flex-row mt-8 max-w-xs md:max-w-7xl gap-12">
            {fetchedData ? (
              fetchedData.trips.map((tripDetails, tripIndex) => (
                <BaseCard
                  key={tripIndex}
                  tripDetails={tripDetails}
                  segments={tripDetails.segments}
                />
              ))
            ) : (
              <div className="flex flex-col text-center">
                <h3 className="font-semibold text-xl text-slate-700">You have no upcoming trips</h3>
                <h3 className="font-semibold text-xl text-orange-700">Add a trip to get started</h3>
              </div>
            )}
          </div>
          <AddTrip
            isAddFlightOpen={isAddFlightOpen}
            closeAddFlight={closeAddFlight}
            setTrips={setTrips}
          />
        </div>
      </ThemeProvider>
    </>
  );
}

export default App;
