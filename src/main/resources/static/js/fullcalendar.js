import * as FullCalendar from "@fullcalendar/core";


document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    // Fetch the appointments for the employee from the backend
    fetch('/employees/1/appointments')
        .then(response => response.json())
        .then(data => {
            // Format the appointment data for FullCalendar
            var events = data.map(appointment => ({
                id: appointment.id,
                title: appointment.title,
                start: appointment.startTimeIso8601,
                end: appointment.endTimeIso8601,
                // Add any additional properties or formatting as needed
            }));

            // Initialize FullCalendar
            var calendar = new FullCalendar.Calendar(calendarEl, {
                // Set the desired options for FullCalendar
                // ...

                // Pass the formatted appointment data to FullCalendar
                events: events,
                // Specify the event time format for display
                eventTimeFormat: {
                    hour: 'numeric',
                    minute: '2-digit',
                    meridiem: 'short'
                },
                // Add event click handler
                eventClick: function(info) {
                    // Handle event click logic here
                    console.log('Event clicked:', info.event);
                }
            });

            // Render the calendar
            calendar.render();
        });
});