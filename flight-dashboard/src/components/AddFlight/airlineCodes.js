/* 
This is a list of all support airlines
Any additional airlines added must keep the list in alphabetical order
*/

const airlineCodes = [
  {
    airlineName: "Aegean Airlines",
    iataCode: "A3",
  },
  {
    airlineName: "Aer Lingus",
    iataCode: "EI",
  },
  {
    airlineName: "Aero República",
    iataCode: "P5",
  },
  {
    airlineName: "Aerolineas Argentinas",
    iataCode: "AR",
  },
  {
    airlineName: "Aeromexico",
    iataCode: "AM",
  },
  {
    airlineName: "Air Baltic",
    iataCode: "BT",
  },
  {
    airlineName: "Air Canada",
    iataCode: "AC",
  },
  {
    airlineName: "Air France",
    iataCode: "AF",
  },
  {
    airlineName: "Air India",
    iataCode: "AI",
  },
  {
    airlineName: "Air Macau",
    iataCode: "NX",
  },
  {
    airlineName: "Air Malta",
    iataCode: "KM",
  },
  {
    airlineName: "Air Mauritius",
    iataCode: "MK",
  },
  {
    airlineName: "Air New Zealand",
    iataCode: "NZ",
  },
  {
    airlineName: "Air Seychelles",
    iataCode: "HM",
  },
  {
    airlineName: "Air Tahiti",
    iataCode: "VT",
  },
  {
    airlineName: "Air Tahiti Nui",
    iataCode: "TN",
  },
  {
    airlineName: "Alaska Airlines",
    iataCode: "AS",
  },
  {
    airlineName: "All Nippon Airways (ANA)",
    iataCode: "NH",
  },
  {
    airlineName: "ANA",
    iataCode: "NH",
  },
  {
    airlineName: "American Airlines",
    iataCode: "AA",
  },
  {
    airlineName: "Asiana",
    iataCode: "OZ",
  },
  {
    airlineName: "Austrian",
    iataCode: "OS",
  },
  {
    airlineName: "Avianca",
    iataCode: "AV",
  },
  {
    airlineName: "British Airways",
    iataCode: "BA",
  },
  {
    airlineName: "Brussels Airlines",
    iataCode: "SN",
  },
  {
    airlineName: "Cathay Pacific",
    iataCode: "CX",
  },
  {
    airlineName: "China Eastern",
    iataCode: "MU",
  },
  {
    airlineName: "China Southern Airlines",
    iataCode: "CZ",
  },
  {
    airlineName: "Condor",
    iataCode: "DE",
  },
  {
    airlineName: "COPA Airlines",
    iataCode: "CM",
  },
  {
    airlineName: "Delta Air Lines",
    iataCode: "DL",
  },
  {
    airlineName: "Egyptair",
    iataCode: "MS",
  },
  {
    airlineName: "EL AL",
    iataCode: "LY",
  },
  {
    airlineName: "Emirates",
    iataCode: "EK",
  },
  {
    airlineName: "Ethiopian Airlines",
    iataCode: "ET",
  },
  {
    airlineName: "Etihad Airways",
    iataCode: "EY",
  },
  {
    airlineName: "EVA Air",
    iataCode: "BR",
  },
  {
    airlineName: "Fiji Airways",
    iataCode: "FJ",
  },
  {
    airlineName: "Finnair",
    iataCode: "AY",
  },
  {
    airlineName: "Garuda",
    iataCode: "GA",
  },
  {
    airlineName: "Hainan Airlines",
    iataCode: "HU",
  },
  {
    airlineName: "Hawaiian Airlines",
    iataCode: "HA",
  },
  {
    airlineName: "Hong Kong Airlines",
    iataCode: "HX",
  },
  {
    airlineName: "Iberia",
    iataCode: "IB",
  },
  {
    airlineName: "Icelandair",
    iataCode: "FI",
  },
  {
    airlineName: "ITA Airways",
    iataCode: "AZ",
  },
  {
    airlineName: "Japan Airlines",
    iataCode: "JL",
  },
  {
    airlineName: "Jet Airways",
    iataCode: "9W",
  },
  {
    airlineName: "JetBlue",
    iataCode: "B6",
  },
  {
    airlineName: "Kenya Airways",
    iataCode: "KQ",
  },
  {
    airlineName: "KLM",
    iataCode: "KL",
  },
  {
    airlineName: "Korean Air",
    iataCode: "KE",
  },
  {
    airlineName: "LOT Polish Airlines",
    iataCode: "LO",
  },
  {
    airlineName: "Lufthansa",
    iataCode: "LH",
  },
  {
    airlineName: "Luxair",
    iataCode: "LG",
  },
  {
    airlineName: "Malaysia Airlines",
    iataCode: "MH",
  },
  {
    airlineName: "Norse Atlantic Airways",
    iataCode: "N0",
  },
  {
    airlineName: "Qantas",
    iataCode: "QF",
  },
  {
    airlineName: "Qatar Airways",
    iataCode: "QR",
  },
  {
    airlineName: "Ryanair",
    iataCode: "FR",
  },
  {
    airlineName: "SAS",
    iataCode: "SK",
  },
  {
    airlineName: "Southwest Airlines",
    iataCode: "WN",
  },
  {
    airlineName: "Spirit Airlines",
    iataCode: "NK",
  },
  {
    airlineName: "SriLankan",
    iataCode: "UL",
  },
  {
    airlineName: "Starlux",
    iataCode: "JX",
  },
  {
    airlineName: "Sun Country Airlines",
    iataCode: "SY",
  },
  {
    airlineName: "SWISS",
    iataCode: "LX",
  },
  {
    airlineName: "TAP Portugal",
    iataCode: "TP",
  },
  {
    airlineName: "Thai Airways",
    iataCode: "TG",
  },
  {
    airlineName: "Turkish Airlines",
    iataCode: "TK",
  },
  {
    airlineName: "United Airlines",
    iataCode: "UA",
  },
  {
    airlineName: "Vietnam Airlines",
    iataCode: "VN",
  },
  {
    airlineName: "Virgin Atlantic",
    iataCode: "VS",
  },
  {
    airlineName: "Virgin Australia",
    iataCode: "VA",
  },
  {
    airlineName: "Westjet",
    iataCode: "WS",
  },
  {
    airlineName: "Wizz Air",
    iataCode: "W4",
  },
  {
    airlineName: "Xiamen Airlines",
    iataCode: "MF",
  },
];

export default airlineCodes;
