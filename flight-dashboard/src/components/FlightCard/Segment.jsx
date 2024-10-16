import { Button, Divider } from "@mui/material";

export default function Segment({ segments }) {
  return (
    <>
      {segments.map((segment, index) => (
        <div key={index} className="flex flex-col min-w-full h-72 snap-center mt-2">
          <h3 className="font-semibold">{segment.airline}</h3>
          <h3 className="font-semibold">
            {segment.dateTime.split("T")[1].slice(0, 5)}{" "}
            {segment.isDelayed ? "(DELAYED)" : "(ON TIME)"}
          </h3>
          <p>Flight {segment.flightNumber}</p>
          <p>{segment.aircraftType}</p>
          <p>{segment.duration}</p>
          <div className="flex min-w-full justify-between mt-4">
            <div className="flex flex-col h-36 justify-between">
              <div className="">
                <h3 className="font-semibold text-center w-32">{segment.origin}</h3>
                <p>Gate {segment.originGate}</p>
                <p>Terminal {segment.originTerminal}</p>
                <p>On time: {segment.onTimePrediction}%</p>
              </div>
              <div className="">
                <Button variant="outlined" size="small">
                  Map
                </Button>
              </div>
            </div>
            <Divider orientation="vertical" variant="middle" flexItem />
            <div className="flex flex-col h-36 justify-between">
              <div className="">
                <h3 className="font-semibold text-center w-32">{segment.destination}</h3>
                <p>Gate {segment.destinationGate}</p>
                <p>Terminal {segment.destinationTerminal}</p>
                <p>Carousel {segment.baggageClaim}</p>
              </div>
              <div className="">
                <Button variant="outlined" size="small" href={segment.checkInLink}>
                  Map
                </Button>
              </div>
            </div>
          </div>
        </div>
      ))}
    </>
  );
}
