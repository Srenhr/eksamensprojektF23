document.addEventListener('DOMContentLoaded', createFormEventListener);


let formEmployee;


function createFormEventListener(){
    formEmployee = document.getElementById("formEmployee");
    formEmployee.addEventListener("submit", handleFormSubmit);
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
    console.log("postFormData");
    const plainFormData = Object.fromEntries(formData.entries())
    console.log("First plainFormData:")
    console.log(plainFormData)
    // TODO commenting this might fix potential issues with postFormData
    const ix = ddRoles.selectedIndex;
    console.log("ix: " + ix)
    const linje = ddRoles[ix]
    console.log("linje: " + linje)
    plainFormData.roles = linje.role // Test for endpoint receiving a roles list instead of just a role
    console.log("Formdata.roles V ")
    console.log(plainFormData.roles)
    console.log("Second plainformdata:")
    console.log(plainFormData);
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