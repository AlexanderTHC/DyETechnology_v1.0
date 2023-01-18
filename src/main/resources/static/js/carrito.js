const input = document.querySelectorAll("input#item_cambiarCantidad-btn"); // Obtener todos los inputs que cambian la cantidad de un item

document.addEventListener("DOMContentLoaded", function()
{
    input.forEach(btn =>
    {
        btn.addEventListener("change", changeItemAmount); // Escuchar cambios en el input
    });
});

async function changeItemAmount(e)
{
    const itemId = e.target.getAttribute("item-id"); // Obtener el id del item del carrito
    const cantidad = e.target.value; // Obtener la cantidad

    // Crear un nuevo form, que hara POST a CarritoController.change()
    const form = new FormData(); 
    form.append("id", itemId);
    form.append("cantidad", cantidad);

    // Enviar peticion
    const api = await fetch(`${window.location.origin}/carrito/cambiar`, 
    {
        method: "POST",
        headers: {"Application-Type": "application/json"},
        body: form
    });

    const response = await api.json(); // Convertir la respuesta del controlador a json
    if(response)
    {
        const {total, subTotal} = response; // Obtener el total y subtotal
        const item = document.querySelector(`#item_subTotal[item-id='${itemId}']`); // Obtener el elemento que muestra el subtotal al cliente
        const cart = document.querySelector("#carrito_total"); // Obtener el elemento que muestra el total al cliente

        item.textContent = subTotal.toLocaleString(); // Cambiar el subtotal del item y convertirlo a formato ARS
        cart.textContent = total.toLocaleString(); // ...
    }
}