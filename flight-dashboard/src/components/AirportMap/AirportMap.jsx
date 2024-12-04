export default function AirportMap(props) {
  ((g) => {
    var h,
      a,
      k,
      p = "The Google Maps JavaScript API",
      c = "google",
      l = "importLibrary",
      q = "__ib__",
      m = document,
      b = window;
    b = b[c] || (b[c] = {});
    var d = b.maps || (b.maps = {}),
      r = new Set(),
      e = new URLSearchParams(),
      u = () =>
        h ||
        (h = new Promise(async (f, n) => {
          await (a = m.createElement("script"));
          e.set("libraries", [...r] + "");
          for (k in g)
            e.set(
              k.replace(/[A-Z]/g, (t) => "_" + t[0].toLowerCase()),
              g[k]
            );
          e.set("callback", c + ".maps." + q);
          a.src = `https://maps.${c}apis.com/maps/api/js?` + e;
          d[q] = f;
          a.onerror = () => (h = n(Error(p + " could not load.")));
          a.nonce = m.querySelector("script[nonce]")?.nonce || "";
          m.head.append(a);
        }));
    d[l]
      ? console.warn(p + " only loads once. Ignoring:", g)
      : (d[l] = (f, ...n) => r.add(f) && u().then(() => d[l](f, ...n)));
  })({
    key: "YOUR_API_KEY",
    v: "weekly",
    // Use the 'v' parameter to indicate the version to use (weekly, beta, alpha, etc.).
    // Add other bootstrap parameters as needed, using camel case.
  });
  return <div className="class"></div>;
}


export function openMap (terminal) {
  switch(terminal){
    case("ATL"):
      window.open("https://www.atl.com/maps-fullscreen/", "_blank");
      break;
    //Africa
    case("JNB"):
     window.open("https://ortambo-airport.com/airport/airport-lounges.html");
     break;
    case("CAI"):
     window.open("https://www.cairo-airport.com/en-us/Services/Passenger-Guide/Terminal-Information");
     break;
    case("ADD"):
     window.open("https://www.addismap.com/#16/8.9863/38.7965");
     break;
    case("CPT"):
     window.open("https://capetown-airport.co.za/terminals/");
     break;
    case("CMN"):
     window.open("https://www.cmnairport.com/transport/");
     break;
    case("ALG"):
     window.open("https://www.aeroport-alger.com/en/map_algiers_airport.php");
     break;
    case("NBO"):
     window.open("https://www.nairobi-airport.com/en/map_nairobi_airport.php");
     break;
    case("HRG"):
     window.open("http://hurghada-airport.co.uk/airport-map-large.htm");
     break;
    case("LOS"):
     window.open("https://www.google.com/maps/place/Murtala+Muhammed+International+Airport/@6.5795986,3.3224706,17z/data=!4m6!3m5!1s0x103b91e751d71485:0xf222ed73a7d14f9a!8m2!3d6.5795773!4d3.3226101!16zL20vMDRjcHhr?entry=ttu&g_ep=EgoyMDI0MTExMy4xIKXMDSoASAFQAw%3D%3D");
     break;
    case("TUN"):
     window.open("https://www.aeroportdetunis.com/en/map_tunis_airport.php");
     break;
     //Asia 
    case("PEK"):
     window.open("https://www.travelchinaguide.com/cityguides/beijing/airport-map.htm");
     break;
    case("DXB"):
     window.open("https://www.airportmaps.com/images/maps_large/DXB_large.png");
     break;
    case("HND"):
     window.open("https://maps.tokyo-haneda.com/?lang=en&s=eyJvbmxpbmUvZ2V0RGlyZWN0aW9uc0Zyb21UbyI6eyJtdWx0aXBvaW50Um91dGluZyI6dHJ1ZX0sIm9ubGluZS9wb2lWaWV3Ijp7Im11bHRpcG9pbnRSb3V0aW5nIjp0cnVlfSwibWFwUmVuZGVyZXIiOnsidnAiOnsibGF0IjozNS41NDg4MTksImxuZyI6MTM5Ljc4MDA5OSwiem9vbSI6MTMuNzM4NjcyLCJiZWFyaW5nIjowLCJwaXRjaCI6MH0sIm9yZCI6M319");
     break;
   case("HKG"):
     window.open("https://www.hongkongairport.com/en/map/");
     break;
   case("PVG"):
     window.open("https://www.travelchinaguide.com/cityguides/shanghai/pudong-airport-maps.htm?srsltid=AfmBOooFRnqKmqgNWRZzWtp9aPjY4-3Vk6SUQrVI_Ofe9QETDSr53XgM");
     break;
   case("DEL"):
     window.open("https://www.newdelhiairport.in/airport-interactive-terminal-maps");
     break;
    case("CAN"):
     window.open("https://www.chinadiscovery.com/guangzhou-tours/maps/guangzhou-airport-maps.html");
     break;
   case("ICN"):
     window.open("https://www.airport.kr/ap/en/map/mapInfo.do");
     break;
   case("CGK"):
     window.open("https://www.ana.co.jp/en/jp/guide/prepare/airport-guide/international/cgk.html");
     break;
   case("SIN"):
     window.open("https://www.changiairport.com/en/at-changi/map.html");
     break;
    //Europe
    case("IST"):
     window.open("https://inmapper.com/istanbul-airport/");
     break;
     case("CDG"):
     window.open("https://www.parisaeroport.fr/en/passengers/access/paris-charles-de-gaulle/terminals-map");
     break;
     case("LHR"):
     window.open("https://maps.heathrow.com/?lang=en&s=eyJvbmxpbmUvZ2V0RGlyZWN0aW9uc0Zyb21UbyI6eyJtdWx0aXBvaW50Um91dGluZyI6dHJ1ZX0sIm9ubGluZS9wb2lWaWV3Ijp7Im11bHRpcG9pbnRSb3V0aW5nIjp0cnVlfSwibWFwUmVuZGVyZXIiOnsidnAiOnsibGF0Ijo1MS40Njk2MDMsImxuZyI6LTAuNDY1MTUyLCJ6b29tIjoxMy4xLCJiZWFyaW5nIjowLCJwaXRjaCI6MH0sIm9yZCI6MX19");
     break;
     case("AMS"):
     window.open("https://www.schiphol.nl/en/airport-maps");
     break;
     case("SVO"):
     window.open("https://www.svo.aero/en/map?terminal=all");
     break;
     case("FRA"):
     window.open("https://www.frankfurt-airport.com/en/airport-guide/orientation/airport-map.html");
     break;
     case("MAD"):
     window.open("https://www.madridairporttravel.com/map/");
     break;
     case("DME"):
     window.open("https://dme.ru/en/airportguide/map/");
     break;
     case("BCN"):
     window.open("https://www.aeropuertobarcelona-elprat.com/ingl/barcelona-airport-map.htm");
     break;
     case("VKO"):
     window.open("https://www.vnukovo.ru/en/for-passengers/airport-map/");
     break;
     //North America  
     case("DFW"):
     window.open("https://map.dfwairport.com/?lang=en&s=eyJvbmxpbmUvZ2V0RGlyZWN0aW9uc0Zyb21UbyI6eyJtdWx0aXBvaW50Um91dGluZyI6dHJ1ZX0sIm9ubGluZS9wb2lWaWV3Ijp7Im11bHRpcG9pbnRSb3V0aW5nIjp0cnVlfSwibWFwUmVuZGVyZXIiOnsidnAiOnsibGF0IjozMi44OTc1NzgsImxuZyI6LTk3LjA0MDQ0Mywiem9vbSI6MTMuODkwOTczLCJiZWFyaW5nIjowLCJwaXRjaCI6MH0sIm9yZCI6MX19");
     break;
     case("DEN"):
     window.open("https://maps.flydenver.com");
     break;
     case("ORD"):
     window.open("https://www.flychicago.com/ohare/map/pages/default.aspx");
     break;
     case("LAX"):
     window.open("https://www.flylax.com/lax%20new-terminal-maps");
     break;
     case("CLT"):
     window.open("https://maps.cltairport.com/?lang=en&s=eyJvbmxpbmUvZ2V0RGlyZWN0aW9uc0Zyb21UbyI6eyJtdWx0aXBvaW50Um91dGluZyI6dHJ1ZX0sIm9ubGluZS9wb2lWaWV3Ijp7Im11bHRpcG9pbnRSb3V0aW5nIjp0cnVlfSwibWFwUmVuZGVyZXIiOnsidnAiOnsibGF0IjozNS4yMjE1MzksImxuZyI6LTgwLjk0MzU2Niwiem9vbSI6MTUuMjI5OTE4LCJiZWFyaW5nIjowLCJwaXRjaCI6MH0sIm9yZCI6Mn19");
     break;
     case("LAS"):
     window.open("https://www.airport.guide/klas#terminal-maps");
     break;
     case("MEX"):
     window.open("https://www.ifly.com/airports/benito-juarez-airport/terminal-map/Terminal-1-map");
     break;
     case("PHX"):
     window.open("https://maps.skyharbor.aero/?lang=en&s=eyJvbmxpbmUvZ2V0RGlyZWN0aW9uc0Zyb21UbyI6eyJtdWx0aXBvaW50Um91dGluZyI6dHJ1ZX0sIm9ubGluZS9wb2lWaWV3Ijp7Im11bHRpcG9pbnRSb3V0aW5nIjp0cnVlfSwibWFwUmVuZGVyZXIiOnsidnAiOnsibGF0IjozMy40MzU1OSwibG5nIjotMTEyLjAwMjU4OCwiem9vbSI6MTQuNDEyNjM0LCJiZWFyaW5nIjowLCJwaXRjaCI6MH0sIm9yZCI6M319");
     break;
     case("MCO"):
     window.open("https://www.airport.guide/kmco#terminal-maps");
     break;
     //South America
     case("GRU"):
     window.open("https://www.gru.com.br/en/passenger/discover-gru/airport-maps/");
     break;
     case("BOG"):
     window.open("https://eldorado.aero/en/mapas?terminal=1&floor=1");
     break;
     case("SCL"):
     window.open("https://www.ifly.com/airports/arturo-merino-benitez-international-airport/terminal-map");
     break;
     case("BSB"):
     window.open("https://airmundo.com/en/airports/brasilia-airport/");
     break;
     case("LIM"):
     window.open("https://www.lima-airport.com/en/mapas");
     break;
     case("CGH"):
     window.open("https://maps.inmapz.com/cgh-congonhas-sao-paulo-airport-4110715625#venue=1355&floor=2028");
     break;
     case("VCP"):
     window.open("https://map-of-sao-paulo.com/airports-maps/international-airport-viracopos-map");
     break;
     case("SDU"):
     window.open("https://map-of-rio-de-janeiro.com/airports-maps/santos-dumont-airport-map");
     break;
     case("REC"):
     window.open("https://airmundo.com/en/airports/recife-guararapes-airport/");
     break;
     case("CNF"):
     window.open("https://site.bh-airport.com.br/SitePages/en/bh-airport/aeroporto.aspx");
     break;
  }
}