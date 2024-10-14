# Flight Dashboard

## Frontend

### Vite + React

Using Vite and React as the frontend framework

Install using `npm create vite@latest -- --template react`

Next, cd into the project directory and run `npm install`

### TailwindCSS

Using TailwindCSS for layout and styling

`npm install -D tailwindcss postcss autoprefixer`

`npx tailwindcss init -p`

Modify `tailwind.config.js` to look like this

```bash
/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}
```

Add Tailwind directives to `./src/index.css`

```bash
@tailwind base;
@tailwind components;
@tailwind utilities;
```

### MaterialUI

MaterialUI provides pre-styled react components

`npm install @mui/material @emotion/react @emotion/styled`

### Launch the dev server

`npm run dev` Note: use `-- --host` flag to enable network dev server (for mobile testing)
