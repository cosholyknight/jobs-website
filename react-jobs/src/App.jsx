import {
  Route,
  createBrowserRouter,
  createRoutesFromElements,
  RouterProvider,
} from "react-router-dom";

import React from "react";
import HomePage from "./pages/Homepage";
import MainLayout from "./layouts/MainLayout";
import JobsPage from "./pages/JobsPage";
import NotFoundPage from "./pages/NotFoundPage";
import JobPage, { jobLoader } from "./pages/JobPage";
import AddJobPage from "./pages/AddJobPage";
import EditJobPage from "./pages/EditJobPage";

const App = () => {
  const addJob = async (newJob) => {
    const res = await fetch("/api/jobs", {
      method: "POST",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify(newJob),
    });
    return;
  };

  const deleteJob = async (id) => {
    const res = await fetch(`/api/jobs/${id}`, {
      method: "DELETE",
    });
    return;
  };

  const updateJob = async (updatedJob, id) => {
    const res = await fetch(`/api/jobs/${id}`, {
      method: "PUT",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify(updatedJob),
    });
    console.log(JSON.stringify(updatedJob));
    return;
  };

  const router = createBrowserRouter(
    createRoutesFromElements(
      <Route path="/" element={<MainLayout />}>
        <Route index element={<HomePage />} />
        <Route path="/jobs" element={<JobsPage />} />
        <Route
          path="/jobs/:id"
          element={<JobPage deleteJob={deleteJob} />}
          loader={jobLoader}
        />
        <Route
          path="/edit-job/:id"
          element={<EditJobPage updateJobSubmit={updateJob} />}
          loader={jobLoader}
        />
        <Route path="*" element={<NotFoundPage />} />
        <Route path="/add-job" element={<AddJobPage addJobSubmit={addJob} />} />
      </Route>
    )
  );
  return <RouterProvider router={router} />;
};

export default App;
