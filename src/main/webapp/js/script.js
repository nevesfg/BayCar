function deleteCar(id){
    let resposta = confirm("Confirmar a exclusão desse carro?");
    if(resposta === true){
        window.location.href = "deleteCar?id=" + id
    }
}

function deleteOlderCar(id){
    let resposta = confirm("Confirmar a exclusão desse carro?");
    if(resposta === true){
        window.location.href = "deleteOlderCar?id=" + id
    }
}

