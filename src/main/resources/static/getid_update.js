const bootcampStatus = document.getElementsByClassName('bootcampStatus');

for (let i = 0; i < bootcampStatus.length; i++) {
    bootcampStatus[i].value = bootcampStatus[i].getAttribute("bootcamp-status");
}

const bootcampId = document.getElementsByClassName('bootcampId');

for (let i = 0; i < bootcampId.length; i++) {
    bootcampId[i].value = bootcampId[i].getAttribute("bootcamp-id");
}
