import React from "react";
import { FaMapMarker } from "react-icons/fa";
import { toast } from "react-toastify";

const CompanyListing = ({ company }) => {
  const deleteCompany = async (id) => {
    const res = await fetch(`/api/companies/${id}`, {
      method: "DELETE",
    });
    return;
  };

  const onDeleteClick = (companyId) => {
    const confirm = window.confirm("Are you sure want to delete this company");
    if (!confirm) return;
    deleteCompany(companyId);
    toast.success("Company deleted successfully!");
  };

  return (
    <div className="bg-white rounded-xl shadow-md relative flex justify-between items-center p-6">
      <div className="flex-1 mr-4">
        <div className="mb-6">
          <h3 className="text-xl font-bold">{company.name}</h3>
        </div>

        <div className="mb-5">{company.description}</div>
        <div className="mb-5 flex items-center">
          <h3 className="text-l font-bold mr-2">Contact Email:</h3>
          <span>{company.contactEmail}</span>
        </div>
        <div className="mb-5 flex items-center">
          <h3 className="text-l font-bold mr-2">Contact Phone:</h3>
          <span>{company.contactPhone}</span>
        </div>

        <div className="border border-gray-100 mb-5"></div>

        <div className="text-orange-700 mb-3">
          <FaMapMarker className="inline text-lg mb-1 mr-1" />
          {company.officeAddress}
        </div>
      </div>

      <div className="flex flex-col items-center">
        <img
          src={`api/${company.logoPath}`}
          alt={company.name}
          className="absolute top-2 right-5 w-20 h-20 object-cover rounded-full mb-2"
        />
        <button
          onClick={() => onDeleteClick(company.id)}
          className="absolute bottom-8 right-5 h-[36px] bg-red-500 hover:bg-red-600 text-white font-bold px-4 py-2 rounded-lg text-center text-sm mt-2"
        >
          Delete Company
        </button>
      </div>
    </div>
  );
};

export default CompanyListing;
