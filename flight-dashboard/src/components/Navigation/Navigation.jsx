import { clearTripsCookie } from "../Cookies/SaveFlights";
import LogoIcon from "../Icons/LogoIcon";
import MobileMenu from "./MobileMenu";
import { Button } from "@mui/material";

export default function Navigation(props) {
  const { setTrips, setFetchedData } = props;
  return (
    <div className="flex flex-row w-full py-4 px-12 justify-between items-center">
      <div className="flex gap-2 items-center md:items-end">
        <LogoIcon width="32" height="32" color="#4338ca" />
        <h3 className="font-semibold md:text-xl">Flight Dashboard</h3>
      </div>
      <div className="flex items-center">
        <div className="invisible md:visible">
          <Button variant="contained" onClick={props.openAddFlight}>
            Add trip
          </Button>
        </div>
        <div className="invisible md:visible">
          <Button onClick={ () => { clearTripsCookie(); setTrips( {trips: []} ); setFetchedData(null); } }>Clear all</Button>
        </div>
        <div className="invisible md:visible">
          <Button>Book Flight</Button>
        </div>
        <div className="visible md:invisible">
          <MobileMenu openAddFlight={props.openAddFlight} />
        </div>
      </div>
    </div>
  );
}
