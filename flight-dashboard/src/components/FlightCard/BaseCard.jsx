import { Button, Divider } from "@mui/material";
import AirplaneIcon from "../Icons/AirplaneIcon";
import Segment from "./Segment";

export default function BaseCard({ tripDetails, segments }) {
  const flightDatetime = new Date(tripDetails.tripStartDateTime);
  const month = String(flightDatetime.getMonth() + 1).padStart(2, "0");
  const day = String(flightDatetime.getDate()).padStart(2, "0");
  const year = flightDatetime.getFullYear();
  const flightDate = `${month}-${day}-${year}`;
  const currentDatetime = new Date();
  const originCity = tripDetails.tripOriginCity.split(":");
  const destinationCity = tripDetails.tripDestinationCity.split(":");
  return (
    <div className="flex flex-col w-96 bg-gray-100 border shadow-sm rounded-sm p-6">
      <h3 className="font-semibold font-mono text-lg text-center">{flightDate}</h3>
      <div className="flex font-bold justify-center gap-2 mb-6">
        <p>{originCity[0].replaceAll("/", ", ")}</p>
        <p className="font-normal">to</p>
        <p>{destinationCity[0].replaceAll("/", ", ")}</p>
      </div>
      <div className="flex justify-around py-2 border-y">
        <p className="text-black">{tripDetails.tripOriginCode}</p>
        <AirplaneIcon color="#000000" width="24" height="24" />
        {tripDetails.layoverAirports.map((airport, index) => (
          <>
            <p className="text-black">{airport}</p>
            <AirplaneIcon color="#000000" width="24" height="24" />{" "}
          </>
        ))}
        <p className="">{tripDetails.tripDestinationCode}</p>
      </div>
      <div className="flex text-center w-full m-0 overflow-x-scroll justify-between snap-x snap-mandatory">
        <Segment segments={segments} />
      </div>
      <div className="flex self-center mt-2">
        <Button
          variant="contained"
          disabled={flightDatetime.getTime() >= currentDatetime.getTime() + 24 * 60 * 60 * 1000}
          href={tripDetails.checkInLink}
        >
          Check In
        </Button>
      </div>
    </div>
  );
}
