document.addEventListener('DOMContentLoaded', () => {

    var form = document.getElementById('appointmentForm');
    var addButton = document.getElementById('createButton');
    var deleteButton = document.getElementById('deleteButton');
    var modalTitle = document.getElementById('modalFormTitle')
    let startTime = document.getElementById("inpStartDateTime")
    let endTime = document.getElementById("inpEndDateTime")

    /*-- ADD APPOINTMENT BUTTON -- */
    addButton.addEventListener('click', function () {
        form.action = 'http://localhost:8080/api/appointment/create';
        deleteButton.hidden = true;
        modalTitle.textContent = 'Opret Aftale'
        document.getElementById('inpId').value = null
        document.getElementById('inpTitle').value = null
        document.getElementById('inpStartDateTime').value = null
        document.getElementById('inpEndDateTime').value = null
        document.getElementById('inpDescription').value = null
    });

    /*-- DELETE APPOINTMENT BUTTON -- */
    deleteButton.addEventListener('click', function () {
        form.action = 'http://localhost:8080/api/appointment/delete';
    });

    /*-- ENDTIME MINIMUM -- */
    startTime.addEventListener('change', function () {
        endTime.min = startTime.value
    });

    /*-- FORM SUBMIT -- */
    form.addEventListener('submit', async function (event) {
        event.preventDefault(); // Prevent form submission

        console.log("nu er vi i submit")

        const URL = form.action;
        let id = document.getElementById('inpId').value
        let title = document.getElementById("inpTitle").value;
        let startTime = document.getElementById("inpStartDateTime").value;
        let endTime = document.getElementById("inpEndDateTime").value;
        let description = document.getElementById("inpDescription").value;

        const data = {
            appointmentId: id,
            title: title,
            startTime: startTime,
            endTime: endTime,
            description: description
        };

        console.log(data)

        const options = {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            mode: 'cors',
            body: JSON.stringify(data)
        };
        await fetch(URL, options)
            .then((res) => {
                console.log(res)
                window.location.href = 'http://localhost:8080/calendar'
                window.location.reload();
            })
            .catch(console.error);


        console.log('Form submitted:', form.id);
    })
})


