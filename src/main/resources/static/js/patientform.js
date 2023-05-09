document.addEventListener('DOMContentLoaded', createFormEventListener);


let formPatient;


function createFormEventListener(){
    formPatient = document.getElementById("formPatient");
    formPatient.addEventListener("submit", handleFormSubmit);
}

async function handleFormSubmit(event) {
    //Vi handler submit her, i stedet for default html behaviour
    event.preventDefault();
    const form = event.currentTarget;
    const url = form.action;
    console.log(form)
    console.log(url)
    //console.log(form === formKommune)
    try {
        const formData = new FormData(form)
        console.log(formData)
        const responseData = await postFormData(url, formData)

        // når vi har oprettet sognet
        alert(formData.get('navn') + ' er oprettet');

        const homeUrl = "/index";
        window.location.replace(homeUrl); //man kan ikke gøre det her indeni en submit button
        //window.location.href = homeUrl;


    } catch (error) {
        alert(error.message)
        console.log(error)
    }

}

async function postFormData(url, formData) {
    const plainFormData = Object.fromEntries(formData.entries())
    //const ix = ddRegioner.selectedIndex;
    //const linje = ddRegioner[ix]
    //plainFormData.region = linje.region
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