# 🧱 SafeZoneBarrier (Minecraft Spigot / Paper Plugin)

![Status](https://img.shields.io/badge/Status-Working%20%2F%20Stable-brightgreen?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge)
![Paper](https://img.shields.io/badge/Minecraft-1.20%2B-blue?style=for-the-badge)
![CI](https://img.shields.io/badge/CI%2FCD-Active-success?style=for-the-badge)

**SafeZoneBarrier** is a high-performance Minecraft server plugin (Spigot / Paper) designed to enforce safe zone boundaries using particle visual effects and barrier collision rules.

---

## 📌 Project Status

- **Status:** 🟢 **Working / Stable**
- **CI/CD:** Automated GitHub Actions Maven build workflow enabled.
- **Integrations:** WorldGuard and CombatLogX hooks included.

---

## 🚀 Key Features

- **Visual Barrier Enforcement:** Displays particle effects and blocks movement when players attempt to enter or exit safe zones during combat.
- **Combat Protection:** CombatLogX hook prevents combat-tagged players from escaping into safe areas.
- **Configurable Settings:** Customize messages, particle types, and cooldowns via `src/main/resources/config.yml`.

---

## 🛠️ Build & Installation

```bash
mvn clean package
```
Move the compiled `.jar` file from `target/` into your server's `plugins/` directory.

---

## 📄 License

Licensed under the MIT License.
