import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const AddCompanyPage = ({ addCompanySubmit }) => {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [officeAddress, setOfficeAddress] = useState("");
  const [contactEmail, setContactEmail] = useState("");
  const [contactPhone, setContactPhone] = useState("");
  const [logoFile, setLogoFile] = useState(null);

  const navigate = useNavigate();

  const submitForm = (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("name", name);
    formData.append("description", description);
    formData.append("officeAddress", officeAddress);
    formData.append("contactEmail", contactEmail);
    formData.append("contactPhone", contactPhone);
    if (logoFile) {
      formData.append("logoFile", logoFile);
    }
    addCompanySubmit(formData);

    toast.success("Company added successfully!");
    return navigate("/companies");
  };

  return (
    <section className="bg-indigo-50">
      <div className="container m-auto max-w-2xl py-24">
        <div className="bg-white px-6 py-8 mb-4 shadow-md rounded-md border m-4 md:m-0">
          <form onSubmit={submitForm}>
            <h2 className="text-3xl text-center font-semibold mb-6">
              Add Company
            </h2>

            <div className="mb-4">
              <label className="block text-gray-700 font-bold mb-2">
                Company Name
              </label>
              <input
                type="text"
                id="name"
                name="name"
                className="border rounded w-full py-2 px-3 mb-2"
                placeholder="eg. Google"
                required
                value={name}
                onChange={(e) => {
                  setName(e.target.value);
                }}
              />
            </div>

            <div className="mb-4">
              <label
                htmlFor="description"
                className="block text-gray-700 font-bold mb-2"
              >
                Description
              </label>
              <textarea
                id="description"
                name="description"
                className="border rounded w-full py-2 px-3"
                rows="4"
                placeholder="Add any company description"
                value={description}
                onChange={(e) => {
                  setDescription(e.target.value);
                }}
              ></textarea>
            </div>

            <div className="mb-4">
              <label className="block text-gray-700 font-bold mb-2">
                Office Address
              </label>
              <input
                type="text"
                id="office_address"
                name="office_address"
                className="border rounded w-full py-2 px-3 mb-2"
                placeholder="Company Office Address"
                required
                value={officeAddress}
                onChange={(e) => {
                  setOfficeAddress(e.target.value);
                }}
              />
            </div>

            <div className="mb-4">
              <label
                htmlFor="contact_email"
                className="block text-gray-700 font-bold mb-2"
              >
                Contact Email
              </label>
              <input
                type="email"
                id="contact_email"
                name="contact_email"
                className="border rounded w-full py-2 px-3"
                placeholder="Email address for applicants"
                required
                value={contactEmail}
                onChange={(e) => {
                  setContactEmail(e.target.value);
                }}
              />
            </div>
            <div className="mb-4">
              <label
                htmlFor="contact_phone"
                className="block text-gray-700 font-bold mb-2"
              >
                Contact Phone
              </label>
              <input
                type="tel"
                id="contact_phone"
                name="contact_phone"
                className="border rounded w-full py-2 px-3"
                placeholder="Optional phone for applicants"
                value={contactPhone}
                onChange={(e) => {
                  setContactPhone(e.target.value);
                }}
              />
            </div>

            <div className="mb-4">
              <label
                htmlFor="logo_file"
                className="block text-gray-700 font-bold mb-2"
              >
                Company Logo
              </label>
              <input
                type="file"
                id="logo_file"
                name="logo_file"
                accept="image/*"
                className="border rounded w-full py-2 px-3 mb-2"
                onChange={(e) => {
                  setLogoFile(e.target.files[0]);
                }}
              />
            </div>

            <div>
              <button
                className="bg-indigo-500 hover:bg-indigo-600 text-white font-bold py-2 px-4 rounded-full w-full focus:outline-none focus:shadow-outline"
                type="submit"
              >
                Add Company
              </button>
            </div>
          </form>
        </div>
      </div>
    </section>
  );
};

export default AddCompanyPage;
