document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['dayGrid'],
        events: 'http://localhost:8080/appointments', // URL to fetch the appointments data
    });
    calendar.render();
});
