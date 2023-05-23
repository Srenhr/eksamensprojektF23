const ddPatients = document.getElementById("ddPatients");
const getPatientsUrl = "http://localhost:8080/getPatients";

function fetchAny(url) {
    return fetch(url).then((response) => response.json())
}

let lstPatients = [];

async function fetchPatients() {
    lstPatients = await fetchAny(getPatientsUrl);

    lstPatients.forEach(fillPatientsDropDown);
}

function fillPatientsDropDown(patient) {
    const el = document.createElement("option");
    el.textContent = patient.name;
    el.value = patient;

    ddPatients.appendChild(el);
}

document.addEventListener('DOMContentLoaded', createAppointmentFormEventListener);


let formShowtime;

function createAppointmentFormEventListener(){
    formShowtime = document.getElementById("appointmentForm");
    formShowtime.addEventListener("submit", handleAppointmentFormSubmit);
}



async function handleAppointmentFormSubmit(event) {
    //Vi handler submit her, i stedet for default html behaviour
    event.preventDefault();
    const form = event.currentTarget;
    const url = form.action;
    console.log(form)
    console.log(url)
    // console.log(form === formUser)
    try {
        const formData = new FormData(form);
        console.log(formData);
        const responseData = await postFormData(url, formData)

        // Når vi har oprettet appointment
        // alert(formData.get('showtime') + ' er oprettet');

        const homeUrl = "index";
        window.location.replace(homeUrl); // Man kan ikke gøre det her indeni en submit button
        //window.location.href = homeUrl;

    } catch (error) {
        alert(error.message)
        console.log(error)
    }
}

async function postFormData(url, formData) {
    const plainFormData = Object.fromEntries(formData.entries())
    const ddIndex = ddPatients.selectedIndex;
    console.log(ddIndex);
    const selectedPatient = ddPatients[ddIndex];
    console.log(selectedPatient);
    plainFormData.patient = selectedPatient.patient; //THIS MIGHT MAKE IT WORK?
    console.log(plainFormData);
    const formDataJsonString = JSON.stringify(plainFormData);
    const fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
            /*"Content-Type": "text/plain"*/
        },
        body: formDataJsonString
    }

    const response = await fetch(url, fetchOptions)

    if (!response.ok) {
        const errorMessage = await response.text()
        throw new Error(errorMessage)
    }
    return response.json();
}
