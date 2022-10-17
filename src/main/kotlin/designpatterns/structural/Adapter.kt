package designpatterns.structural

// Define what the phone takes to charge (UsbTypeC)
// Define the charger we have (EUPlug with UsbTypeMini)
// Define the plugs (USPlug and EUPlug)
// Define the usb types (UsbTypeC and UsbTypeMini)
// Use Adapter design pattern to adapt to the things we want to charge the phone

interface IEUPlug {
    fun hasPower(): Int // 1 on 0 off
}

interface IUSPlug {
    fun hasPower(): String // "ON" / "OFF"
}

class EUPlug : IEUPlug {
    override fun hasPower() = 1
}

class USPlug : IUSPlug {
    override fun hasPower() = "ON"
}

interface IUsbTypeC {
    fun hasPower(): Boolean
}

interface IUsbTypeMini {
    fun hasPower(): Power
}

class UsbTypeMini : IUsbTypeMini {
    override fun hasPower() = Power.TRUE
}

enum class Power {
    TRUE, FALSE
}


class UsbTypeC : IUsbTypeC {
    override fun hasPower() = true
}

// the charger we have in US, has an IEUPlug connected to an IUsbTypeMini
fun charger(euPlug: IEUPlug): IUsbTypeMini {
    return object : IUsbTypeMini {
        override fun hasPower() = when (euPlug.hasPower()) {
            1 -> Power.TRUE
            else -> Power.FALSE
        }
    }
}

fun mobile(usbTypeC: IUsbTypeC) {
    when (usbTypeC.hasPower()) {
        true -> println("phone is charging")
        else -> println("phone is NOT charging")
    }
}

fun main() {
    mobile(charger(USPlug().toEuPlug()).toUsbC()) // case when in US
}

// Create the Adapters

// Adapter to convert IUSPlug to a IEUPlug
fun IUSPlug.toEuPlug(): IEUPlug {
    val usPower = this.hasPower()
    return object : IEUPlug {
        override fun hasPower() = when (usPower) {
            "ON" -> 1
            else -> 0
        }
    }
}

// Adapter to convert IUsbTypeMini to a IUsbTypeC
fun IUsbTypeMini.toUsbC(): IUsbTypeC {
    val usbTypeMiniPower = this.hasPower()
    return object : IUsbTypeC {
        override fun hasPower() = when (usbTypeMiniPower) {
            Power.TRUE -> true
            Power.FALSE -> false
        }

    }
}
