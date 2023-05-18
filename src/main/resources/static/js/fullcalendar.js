import * as FullCalendar from "@fullcalendar/core";

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    // Fetch appointments and initialize FullCalendar
    fetch('http://localhost:8080/employees/1/appointments')
        .then(response => response.json())
        .then(data => {
            // Format the appointment data for FullCalendar
            var events = data.map(appointment => ({
                id: appointment.appointmentId,
                title: appointment.user.firstName + ' ' + appointment.user.lastName,
                start: appointment.startTimeIso8601,
                end: appointment.endTimeIso8601,
                // Add any additional properties or formatting as needed
            }));

            function formatTime(time) {
                var date = new Date(time);
                var hour = date.getHours();
                var minute = date.getMinutes();
                return ('0' + hour).slice(-2) + ':' + ('0' + minute).slice(-2);
            }

            // Initialize FullCalendar
            var calendar = new FullCalendar.Calendar(calendarEl, {
                // Set the desired options for FullCalendar
                // ...

                // Pass the formatted appointment data to FullCalendar
                events: events,
                // Specify the event time format for display
                eventTimeFormat: {
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: false,
                    hour24: true
                },
                // Add event click handler
                eventClick: function(info) {
                    var appointmentId = info.event.id

                    // Potential redirect into the chosen appointment should be possible if client wants functionality.
                    window.location.href = '/appointments/' + appointmentId;

                    console.log('Event clicked:', info.event);
                }
            });

            // Render the calendar
            calendar.render();
        });
});