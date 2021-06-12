const addProfessores = document.getElementsByClassName('addProfessor');

for (let i = 0; i < addProfessores.length; i++) {
    addProfessores[i].value = addProfessores[i].getAttribute("bootcamp-id");
}

const addCandidatos = document.getElementsByClassName('addCandidato');

for (let i = 0; i < addCandidatos.length; i++) {
    addCandidatos[i].value = addCandidatos[i].getAttribute("bootcamp-id");
}
