const cambiarCantidadBtn = document.querySelectorAll("input#sumarCantidadBtn");


document.addEventListener("DOMContentLoaded", function()
{
    cambiarCantidadBtn.forEach(btn =>
    {
        btn.addEventListener("change", cambiarCantidadasheee);
    });
});

async function cambiarCantidadasheee(e)
{
    const carritoId = e.target.getAttribute("carrito-id");
    const cantidad = e.target.value;

    const form = new FormData();
    form.append("id", carritoId);
    form.append("cantidad", cantidad);

    const api = await fetch(`${window.location.origin}/carrito/sumarcantidaddenasheeeee`,
    {
        method: "POST",
        body: form
    });

    const response = await api.json();

    console.log(response);

}