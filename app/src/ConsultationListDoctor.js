import React, { Component } from "react";
import axios from "axios";
import Input from "./Input";
import TextArea from "./TextArea";

export default function ConsultationListDoctor() {
	const consultationsarr = [ {consultationFor:"abc",healthIssue:"dsa",durationOfHealthIssue:"28 days",additionalQuery:"jjag",consultationResponse:"hhga",status:"responded"},
		 {consultationFor:"abc",healthIssue:"dsa",durationOfHealthIssue:"28 days",additionalQuery:"jjag",consultationResponse:"hhga",status:"responded"}];
	
	const consultations = async(event) =>  {
		let origin;

	    if (!window.location.origin) {
	      origin = window.location.protocol + "//" + window.location.hostname + 
	         (window.location.port ? ':' + window.location.port: '');
	    }
	    origin = window.location.origin;
	    axios
	      .get(
	        origin+"/rest/urna/consultation/consultations",
	      )
	      .then(response => {
	    	  return response.data;
	      })
	      
	}
	
	const consultationsListRender = (consultations, index) => {
		return (
				<tr key={index}>
				<td>{consultations.consultationFor}</td>
				<td>{consultations.healthIssue}</td>
				<td>{consultations.durationOfHealthIssue}</td>
				<td>{consultations.additionalQuery}</td>
				<td>{consultations.consultationResponse}</td>
				<td>{consultations.status}</td>
				</tr>
				)
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
	{consultationsarr.map(consultationsListRender)}
	</tbody>
	</table>	
	); 
};
