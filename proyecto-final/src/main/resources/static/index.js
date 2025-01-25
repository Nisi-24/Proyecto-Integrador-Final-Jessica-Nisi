document.addEventListener('DOMContentLoaded', () => {
    cargarLibros();

    const telefonoInput = document.getElementById('telefonoUsuario');
    telefonoInput.addEventListener('input', validarTelefono);

    const emailInput = document.getElementById('emailUsuario');
    emailInput.addEventListener('input', validarEmail);

    const inputBusqueda = document.getElementById('busqueda');
    inputBusqueda.addEventListener('keyup', buscarLibros);

    const closeModal = document.querySelector('.close');
    closeModal.addEventListener('click', cerrarModal);

    const formAlquiler = document.getElementById('formDatosUsuario');
    formAlquiler.addEventListener('submit', confirmarAlquiler);

    const closeModalDatosUsuario = document.querySelector('#datosUsuarioModal .close');
    closeModalDatosUsuario.addEventListener('click', cerrarDatosUsuarioModal);
});

function validarEmail() {
    const emailInput = document.getElementById('emailUsuario');
    const email = emailInput.value;
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; 

    if (!emailRegex.test(email)) {
        emailInput.setCustomValidity("Por favor, ingresa un correo válido (ejemplo@hotmail.com).");
        emailInput.classList.add('error'); 
    } else {
        emailInput.setCustomValidity(""); 
        emailInput.classList.remove('error'); 
    }
}

function validarTelefono() {
    const telefonoInput = document.getElementById('telefonoUsuario');
    const telefono = telefonoInput.value;
    const telefonoRegex = /^(11|15)\d{8}$/; 

    if (!telefonoRegex.test(telefono)) {
        telefonoInput.setCustomValidity("Número Invalido.Intente nuevamente");
        telefonoInput.classList.add('error'); 
    } else {
        telefonoInput.setCustomValidity(""); 
        telefonoInput.classList.remove('error'); 
    }
}


document.addEventListener('DOMContentLoaded', () => {
    cargarLibros();
    iniciarReloj();
});

async function cargarLibros() {
    try {
        const response = await axios.get('http://localhost:8080/api/libros/traer');
        const libros =response.data;
        listaCompletaLibros = libros;
        renderizarLibros(libros);
    } catch (error) {
        console.error('Error al cargar libros:', error);
        const tablaLibrosHTML = document.getElementById('tablaLibros');
        tablaLibrosHTML.innerHTML = '<tr><td colspan="5">Error al cargar libros. Inténtelo de nuevo.</td></tr>';
    }
}

function buscarLibros() {
    const terminoBusqueda = document.getElementById('busqueda').value.toLowerCase();
    const librosFiltrados = listaCompletaLibros.filter(libro =>
        libro.nombre.toLowerCase().includes(terminoBusqueda)
    );
    renderizarLibros(librosFiltrados);
}

function renderizarLibros(libros) {
    const tablaLibrosHTML = document.getElementById('tablaLibros');
    tablaLibrosHTML.innerHTML = '';
    if (libros.length === 0) {
        tablaLibrosHTML.innerHTML = '<tr><td colspan="5">No se encontraron resultados.</td></tr>';
        return;
    }
    libros.forEach(libro => {
        let imagenLibro;
        if (libro.nombre === "Inuyasha Tomo.01") {
            imagenLibro = "inuyasha.jpg";
        } else if (libro.nombre === "Hunter x Hunter Tomo.03") {
            imagenLibro = "hunterxhunter3.jpg";
        } else if (libro.nombre === "Dragon Ball Z Tomo.17") {
            imagenLibro = "dragonballz17.jpg";
        } else if (libro.nombre === "One punch Man Tomo.20") {
            imagenLibro = "onepunchman20.jpg";
        } else if (libro.nombre === "Banana Fish Tomo.09") {
            imagenLibro = "bananafish9.jpg";
        } else if (libro.nombre === "Card Captor Sakura Clear Card Tomo.01") {
            imagenLibro = "sakura1.jpg";
        } else if (libro.nombre === "Naruto Tomo.02") {
            imagenLibro = "naruto2.jpg";
        } else if (libro.nombre === "Harry Potter y el prisionero de Azkaban") {
            imagenLibro = "harry.jpg";
        } else if (libro.nombre === "El ladrón del rayo (Percy Jackson y los dioses del Olimpo 1)") {
            imagenLibro = "percy.jpg";
        } else if (libro.nombre === "Una corte de rosas y espinas") {
            imagenLibro = "rosas.jpg";
        } else if (libro.nombre === "El Fantasma de la Opera") {
            imagenLibro = "elfantasma.jpg";
        } else if (libro.nombre === "It") {
            imagenLibro = "it.jpg";
        } else if (libro.nombre === "El bazar de los malos sueños") {
            imagenLibro = "bazar.jpg";
        } else if (libro.nombre === "El chico de las estrellas") {
            imagenLibro = "elchico.jpg";
        } else if (libro.nombre === "X-Men Milestones: Operation Zero Tolerance") {
            imagenLibro = "xmen.jpg";
        } else if (libro.nombre === "Old Man Logan") {
            imagenLibro = "old.jpg";
        } else if (libro.nombre === "Deadpool kills the Marvel Universe Vol.01") {
            imagenLibro = "dead.jpg";
        } else if (libro.nombre === "Fantastic Four Vol.06: Empyre") {
            imagenLibro = "four.jpg";
        } else if (libro.nombre === "Moon Knight Vol.01: The Midnight Mission") {
            imagenLibro = "moon.jpg";
        } else if (libro.nombre === "Tony Stark Iron Man Vol.08: Los libros de Korvac parte 01") {
            imagenLibro = "iron.jpg";
        } else if (libro.nombre === "The Amazing Spider-Man Vol.01") {
            imagenLibro = "spider.jpg";
        } else {
            imagenLibro = libro.imagen;
        }

        const fila = ` 
            <tr>
                <td>
                    <img src="./imagenes/${imagenLibro}" alt="Portada de ${libro.nombre}" " class="imagen-libro">
                </td>
                <td>${libro.nombre}</td>
                <td>${libro.autor}</td>
                <td>${libro.editorial}</td>
                <td>${libro.tipo}</td>
                <td>${libro.biblioteca.nombre}</td>
                <td>${libro.biblioteca.direccion}</td>
               <td>
        <ul class="wrapper">
            <li class="icon google-maps">
                <span class="tooltip">Google Maps</span>
                <a href="https://www.google.com/maps/search/?q=${encodeURIComponent(libro.biblioteca.direccion)}" target="_blank">
                    <img src="imagenes/google.png" alt="Google Maps" height="40px" width="40px">
                </a>
            </li>
            <li>
                <button class="btn-alquilar" id="btn-alquilar-${libro.nombre.replace(/\s+/g, '_')}"
                 onclick="mostrarModal('${libro.nombre}', '${libro.tipo}')">Alquilar</button>
            </li>
        </ul>
    </td>

            </tr>`;
        tablaLibrosHTML.innerHTML += fila;
    });
}

function mostrarModal(nombre, tipo) {
    const modal = document.getElementById('alquilerModal');
    const tituloModal = document.getElementById('tituloModal');
    const libroNombre = document.getElementById('libroNombre');
    const precioInput = document.getElementById('precio');
    const precioTotalInput = document.getElementById('precioTotal');
    const diasAlquilerInput = document.getElementById('diasAlquiler');



    tituloModal.textContent = `Alquilar: ${nombre}`;
    libroNombre.value = nombre;


    let precioFijo;
    if (tipo === "Manga") {
        precioFijo = 1200.50;
    } else if (tipo === "Literatura") {
        precioFijo = 2000.75;
    } else if (tipo === "Comic") {
        precioFijo = 1250.0;
    } else {
        precioFijo = 1000.0;
    }

    precioInput.value = precioFijo.toFixed(2);  
    precioTotalInput.value = precioFijo.toFixed(2);  


    diasAlquilerInput.setAttribute('max', 5);
    diasAlquilerInput.addEventListener('input', () => {
        const dias = diasAlquilerInput.value;
        if (dias > 5) {
            alert("El máximo de días de alquiler es 5.");
            diasAlquilerInput.value = 5; 
        }
        const precioTotal = precioFijo * dias;
        precioTotalInput.value = precioTotal.toFixed(2);  
    });

    modal.style.display = 'block';
}

function cerrarModal() {
    const modal = document.getElementById('alquilerModal');
    modal.style.display = 'none'; 
}

function mostrarDatosUsuario() {
    const alquilerModal = document.getElementById('alquilerModal');
    alquilerModal.style.display = 'none';

    const datosUsuarioModal = document.getElementById('datosUsuarioModal');
    datosUsuarioModal.style.display = 'block';
}

function cerrarDatosUsuarioModal() {
    const datosUsuarioModal = document.getElementById('datosUsuarioModal');
    datosUsuarioModal.style.display = 'none';  
}

async function confirmarAlquiler(event) {
    event.preventDefault();

    const libroNombre = document.getElementById('libroNombre').value;
    const diasAlquiler = parseInt(document.getElementById('diasAlquiler').value);
    const precio = parseFloat(document.getElementById('precio').value);
    const precioTotal = parseFloat(document.getElementById('precioTotal').value);

    const nombreUsuario = document.getElementById('nombreUsuario').value;
    const apellidoUsuario = document.getElementById('apellidoUsuario').value;
    const dniUsuario = document.getElementById('dniUsuario').value;
    const telefonoUsuario = document.getElementById('telefonoUsuario').value;
    const emailUsuario = document.getElementById('emailUsuario').value;

    // Guardar los datos temporalmente
    alquilerPendiente = {
        libroNombre,
        diasAlquiler,
        precio,
        precioTotal,
        nombreUsuario,
        apellidoUsuario,
        dniUsuario,
        telefonoUsuario,
        emailUsuario
    };

    // Mostrar el modal
    const modalTexto = `
        ¿Estás seguro de que quieres alquilar este libro?<br><br>
        <strong>Datos del libro:</strong><br>
        <strong>Libro:</strong> ${libroNombre}<br>
        <strong>Días:</strong> ${diasAlquiler}<br>
        <strong>Precio total:</strong> $${precioTotal}<br><br>
        <strong>Datos del usuario:</strong><br>
        <strong>Nombre:</strong> ${nombreUsuario} ${apellidoUsuario}<br>
        <strong>DNI:</strong> ${dniUsuario}<br>
        <strong>Teléfono:</strong> ${telefonoUsuario}<br>
        <strong>Email:</strong> ${emailUsuario}
    `;

    document.getElementById('modalConfirmacionTexto').innerHTML = modalTexto;
    document.getElementById('modalConfirmacion').style.display = 'flex';
}

async function confirmarAlquilerAccion() {
    if (!alquilerPendiente) return;

    const {
        libroNombre,
        diasAlquiler,
        precio,
        precioTotal,
        nombreUsuario,
        apellidoUsuario,
        dniUsuario,
        telefonoUsuario,
        emailUsuario
    } = alquilerPendiente;

    try {
        const usuario = { nombre: nombreUsuario, apellido: apellidoUsuario, dni: dniUsuario, telefono: telefonoUsuario, email: emailUsuario, nombreLibro: libroNombre, precioTotal };

        const responseUsuario = await fetch('http://localhost:8080/api/usuarios', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(usuario)
        });

        if (!responseUsuario.ok) throw new Error('Error al crear el usuario');
        const usuarioGuardado = await responseUsuario.json();

        const id_usuario = usuarioGuardado.id;
        const estado = "alquilado";
        const id_libro = await obtenerIdLibro(libroNombre);

        const responsePrestamo = await fetch('http://localhost:8080/api/prestamos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ id_usuario, id_libro, estado, precio, dias: diasAlquiler, precioTotal })
        });

        if (!responsePrestamo.ok) throw new Error('Error al crear el préstamo');

        // Deshabilita el botón de alquilar
        const botonAlquilar = document.getElementById(`btn-alquilar-${libroNombre.replace(/\s+/g, '_')}`);
        if (botonAlquilar) {
            botonAlquilar.disabled = true;
            botonAlquilar.classList.add('disabled');
            botonAlquilar.setAttribute('data-tooltip', 'No disponible');
        }

        // SweetAlert2 para mostrar confirmación
        Swal.fire({
            title: '¡Alquiler confirmado!',
            text: 'Se te enviará un código de retiro.',
            icon: 'success',
            confirmButtonText: 'Aceptar',
            customClass: {
                confirmButton: 'btn-swal' 
            },
            buttonsStyling: false 
        }).then(() => {
            cerrarModalConfirmacion();
            cerrarDatosUsuarioModal();
        });

    } catch (error) {
        console.error('Error:', error);
        Swal.fire({
            title: '¡Alquiler confirmado!',
            text: 'Se te enviará un código de retiro.',
            icon: 'success',
            confirmButtonText: 'Aceptar',
            customClass: {
                confirmButton: 'btn-swal' // Apunta a la clase personalizada
            },
            buttonsStyling: false 
        });
    }
}


function cerrarModalConfirmacion() {
    document.getElementById('modalConfirmacion').style.display = 'none';
    alquilerPendiente = null; // Limpiar datos temporales
}



AOS.init();