const form = document.querySelector("#sendimage");

async function sendData() {
  console.log("funcionou");
    // Associate the FormData object with the form element
    const formData = new FormData(form);
    try {
      const response = await fetch("http://localhost:8080/bike/file", {
        method: "POST",
        // Set the FormData instance as the request body
        body: formData
      }) 

     let responseFinal = await response.json();
      console.log(responseFinal.fileName);
      document.querySelector("#codigo-da-imagem").value = responseFinal.fileName;
    } catch (e) {
      console.error(e);
    } 

  }
  