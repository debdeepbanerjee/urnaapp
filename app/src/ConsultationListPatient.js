import React, {Component} from "react";
import axios from "axios";
import Input from "./Input";
import TextArea from "./TextArea";

const ConsultationRow = ({
                             consultationFor,
                             healthIssue,
                             durationOfHealthIssue,
                             additionalQuery,
                             consultationResponse,
                             status,
                         }) => {
    return (
        <tr>
            <td>{consultationFor}</td>
            <td>{healthIssue}</td>
            <td>{durationOfHealthIssue}</td>
            <td>{additionalQuery}</td>
            <td>{consultationResponse}</td>
            <td>{status}</td>
        </tr>
    );
};

const useConsultationList = () => {
    const [error, setError] = React.useState(false);
    const [loading, setLoading] = React.useState(true);
    const [consultationsList, setConsultations] = React.useState([]);

    // useEffect with empty dependencies list `[]` is equivalent to `componentDidMount()`
    React.useEffect(function loadData() {
        axios
            .get("/rest/urna/consultation/consultations/secure/patient/id/"+window.$pid  )
            .then(response => setConsultations(response.data))
            .catch(() => setError(true))
            .finally(() => setLoading(false));
    }, []);

    return {
        loading,
        error,
        consultationsList
    };
};

export default function ConsultationListPatient() {
    const {
        loading,
        error,
        consultationsList,
    } = useConsultationList();

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>An error occurred...</div>;
    }

    return (
        <table>
            <thead>
            <tr>
                <th>Name of the Patient</th>
                <th>Health Issue</th>
                <th>Duration of the issue</th>
                <th>Additional query</th>
                <th>Response from the doctor</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            {consultationsList.map((consultation, index) => <ConsultationRow key={index} {...consultation} />)}
            </tbody>
        </table>
    );
};
