export default function LogoIcon(props) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width={props.width}
      height={props.height}
      //   strokeWidth={1}
      viewBox="0 0 24 24"
      fill={props.color}
      //   stroke={props.color}
    >
      <path
        strokeLinecap="round"
        strokeLinejoin="round"
        d="M3 18h18v2H3zm18.509-9.473a1.61 1.61 0 0 0-2.036-1.019L15 9 7 6 5 7l6 4-4 2-4-2-1 1 4 4 14.547-5.455a1.611 1.611 0 0 0 .962-2.018z"
      />
    </svg>
  );
}
