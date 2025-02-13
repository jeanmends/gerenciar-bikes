const myModal = document.getElementById('myModal')
const myInput = document.getElementById('myInput')


myModal.addEventListener('shown.bs.modal', () => {
  myInput.focus()
})

function openModal(id, idBike) {
  document.getElementById(id).classList.add('open');
  document.body.classList.add('jw-modal-open');
  
 mostrarBike(idBike);
}

// close currently open modal
function closeModal() {
  document.querySelector('.jw-modal.open').classList.remove('open');
  document.body.classList.remove('jw-modal-open');
}

window.addEventListener('load', function() {
  // close modals on background click
  document.addEventListener('click', event => {
      if (event.target.classList.contains('jw-modal')) {
          closeModal();
      }
  });
});