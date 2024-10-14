import { Button } from "@mui/material";
import AddFlightList from "./AddFlight";

export default function AddTrip(props) {
  return (
    <>
      {props.isAddFlightOpen && (
        <div className="fixed top-0 left-0 z-10 bg-gray-900/50 w-screen h-screen">
          <div
            id="addFlightDialog"
            className="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 bg-gray-50 rounded-sm p-4"
          >
            <h1 className="font-semibold text-lg text-center">Add a trip</h1>
            <div className="flex flex-col gap-4 p-4">
              <AddFlightList />
              <div className="flex justify-center gap-4">
                <Button variant="outlined" onClick={props.closeAddFlight}>
                  Close
                </Button>
                <Button variant="contained" onClick={props.closeAddFlight}>
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
