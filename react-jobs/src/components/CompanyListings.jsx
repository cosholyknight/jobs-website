import React from "react";
import { useState, useEffect } from "react";
import CompanyListing from "./CompanyListing";
import Spinner from "./Spinner";

const CompanyListings = () => {
  // Fetch API
  const [companies, setCompanies] = useState([]);

  // Display loading
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("/api/companies")
      .then((response) => response.json())
      .then((data) => {
        if (data.code === 1000) {
          setCompanies(data.result);
        }
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);
  return (
    <section className="bg-blue-50 px-4 py-10">
      <div className="container-xl lg:container m-auto">
        <h2 className="text-3xl font-bold text-indigo-500 mb-6 text-center">
          Companies
        </h2>

        {loading ? (
          <Spinner />
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            {companies.map((company) => (
              <CompanyListing key={company.id} company={company} />
            ))}
          </div>
        )}
      </div>
    </section>
  );
};

export default CompanyListings;
