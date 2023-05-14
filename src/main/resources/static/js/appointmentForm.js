console.log("appointmentForm")

const appointmentForm = document.getElementById("appointmentForm");

async function handleAppointmentFormSubmit(event) {
    event.preventDefault();
    const form = event.action;
    const url = event.target;
    console.log(form)
    console.log(url)
    try {
        const formData = new FormData(form)
        console.log(formData)
        const responseData = await postAppointmentFormData(url, formData)

        alert(formData.get('navn') + ' er oprettet');

        const homeUrl = "index.html";
        window.location.replace(homeUrl); //man kan ikke gøre det her indeni en submit button
        //window.location.href = homeUrl;

    } catch (error) {
        alert(error.message)
        console.log(error)
    }
}

async function postAppointmentFormData(url, formData) {
    const plainFormData = Object.fromEntries(formData.entries())

    console.log(plainFormData)
    const formDataJsonString = JSON.stringify(plainFormData)
    const fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
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





