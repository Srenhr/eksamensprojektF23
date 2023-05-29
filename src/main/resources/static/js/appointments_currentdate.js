// Get the current date
var currentDate = new Date().toISOString().split('T')[0];

// Fetch appointments from the API endpoint
fetch('http://localhost:8080/api/appointments')
    .then(response => response.json())
    .then(appointments => {
        // Filter appointments for the current date
        var filteredAppointments = appointments.filter(function (appointment) {
            var appointmentDate = appointment.startTime.split('T')[0];
            return currentDate >= appointmentDate && currentDate <= appointment.endTime.split('T')[0];
        });

        // Generate the table rows dynamically
        var tableBody = document.getElementById('appointmentBody');
        filteredAppointments.forEach(function (appointment) {
            var row = document.createElement('tr');
            row.innerHTML = `
            <td>${appointment.title}</td>
            <td>${appointment.description}</td>
            <td>${appointment.startTime}</td>
            <td>${appointment.endTime}</td>
          `;
            tableBody.appendChild(row);
        });
    })
    .catch(error => {
        console.error('Error fetching appointments:', error);
    });
