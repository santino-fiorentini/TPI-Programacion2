# 🍔 Trabajo Práctico Integrador – Programación 2

## Food Store – Sistema de Gestión de Pedidos de Comida

---

### 🏫 Datos de la Universidad y la Cátedra

**Carrera:** Tecnicatura Universitaria en Programación  
**Materia:** Programación 2

---

### 👥 Grupo 56
| Nombre | Comisión |
|---------|-----------|
| **Santino Fiorentini** | Comisión 4 |
| **David Kenny** | Comisión 7 |

---

## 🧠 Descripción General del Proyecto

Food Store es una aplicación de consola desarrollada en Java como parte del Trabajo Práctico Integrador de la materia Programación 2.

El sistema permite administrar categorías, productos, usuarios y pedidos mediante operaciones CRUD (Crear, Leer, Actualizar y Eliminar), aplicando los conceptos fundamentales de Programación Orientada a Objetos trabajados durante la cursada.

Entre las principales funcionalidades se encuentran:

* Gestión de categorías.
* Gestión de productos.
* Gestión de usuarios.
* Gestión de pedidos.
* Validaciones de datos.
* Manejo de excepciones personalizadas.
* Bajas lógicas.
* Cálculo automático de subtotales y totales.

---

## ⚙️ Estructura del Proyecto

```text
FoodStore/
│
│
├── src/
│   │
│   ├── entities/
│   │   ├── Base.java
│   │   ├── Categoria.java
│   │   ├── Producto.java
│   │   ├── Usuario.java
│   │   ├── Pedido.java
│   │   └── DetallePedido.java
│   │
│   ├── enums/
│   │   ├── Estado.java
│   │   ├── FormaPago.java
│   │   └── Rol.java
│   │
│   ├── exceptions/
│   │   ├── CategoriaDuplicadaException.java
│   │   ├── EntidadNoEncontradaException.java
│   │   ├── MailDuplicadoException.java
│   │   ├── PrecioInvalidoException.java
│   │   └── StockInvalidoException.java
│   │
│   ├── interfaces/
│   │   └── Calculable.java
│   │
│   ├── services/
│   │   ├── CategoriaService.java
│   │   ├── ProductoService.java
│   │   ├── UsuarioService.java
│   │   └── PedidoService.java
│   │
│   ├── ui/
│   │   ├── MenuPrincipal.java
│   │   ├── MenuCategoria.java
│   │   ├── MenuProducto.java
│   │   ├── MenuUsuario.java
│   │   └── MenuPedido.java
│   │
│   └── Main.java
│
├── FoodStore.pdf
│
└── README.md
```

---

## ☕ Requisitos

* Java 21 o superior
* Apache NetBeans (recomendado)
* Git (opcional)

---

## ▶️ Instrucciones de Ejecución

1. Clonar o descargar el repositorio.
2. Abrir el proyecto en Apache NetBeans.
3. Verificar que el JDK configurado sea Java 21 o superior.
4. Ejecutar la clase:

```text
Main.java
```

5. Utilizar el menú principal para acceder a las distintas funcionalidades del sistema.

---

## 🧩 Ejemplos de Código Real

### 🟦 Clase abstracta Base

```java
public abstract class Base {

    protected Long id;
    protected boolean eliminado;
    protected LocalDateTime createdAt;

    public Base() {
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }
}
```

---

### 🟦 Interfaz Calculable

```java
public interface Calculable {
    Double calcularTotal();
}
```

---

### 🟦 Implementación del cálculo de total

```java
@Override
public Double calcularTotal() {
    return detallesPedido.stream()
            .mapToDouble(DetallePedido::getSubtotal)
            .sum();
}
```

---

### 🟦 Validación de mail duplicado

```java
public boolean existeMail(String mail) {
    return usuarios.stream()
            .anyMatch(u ->
                    u.getMail()
                     .equalsIgnoreCase(mail));
}
```

---

### 🟦 Baja lógica

```java
public void eliminar(Long id) {
    Usuario usuario = buscarPorId(id);
    usuario.setEliminado(true);
}
```

---

### 🟦 Manejo de excepciones

```java
if (existeMail(mail)) {
    throw new MailDuplicadoException(
            "El mail ya se encuentra registrado."
    );
}
```

---

## 🧮 Ejemplos de Ejecución

### ➕ Creación de categoría

**Entrada**

```text
Ingrese nombre:
Hamburguesas

Ingrese descripción:
Productos principales
```

**Salida**

```text
Categoría creada correctamente.
ID generado: 1
```

---

### 👤 Creación de usuario

**Entrada**

```text
Nombre: Santino
Apellido: Fiorentini
Mail: sf@gmail.com
```

**Salida**

```text
Usuario creado correctamente.
```

---

### ❌ Error por mail duplicado

**Entrada**

```text
Mail: sf@gmail.com
```

**Salida**

```text
Error: El mail ya se encuentra registrado.
```

---

### 🛒 Creación de pedido

**Salida**

```text
Pedido creado correctamente.

Cliente: Juan Pérez
Productos: 2

Total: $18.500
```

---

## 📄 Documentación

La documentación académica y técnica completa del proyecto se encuentra disponible en:

```text
FoodStore.pdf
```

---

## 🧾 Conclusión

Este proyecto permitió aplicar los principales conceptos desarrollados durante la cursada de Programación 2, especialmente Programación Orientada a Objetos, herencia, interfaces, colecciones y manejo de excepciones.

A través del desarrollo de Food Store se construyó una aplicación funcional para la gestión de categorías, productos, usuarios y pedidos, incorporando validaciones, organización por paquetes y separación de responsabilidades.

---

## 📘 Bibliografía

* Material de Cátedra de Programación 2.
