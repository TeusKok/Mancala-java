import { createBrowserRouter } from "react-router-dom";
import { RootLayout } from "./layouts/RootLayout";
import { Start } from "./pages/Start";
import { ErrorPage } from "./pages/ErrorPage";
import { About } from "./pages/About";
import { Play } from "./pages/Play";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <RootLayout />,
        errorElement: <ErrorPage />,
        children: [
            {
                index: true,
                element: <Start />
            },
            {
                path: "play",
                element: <Play />,
            },
            {
                path: "about",
                element: <About />
            }
        ]
    }
]);
