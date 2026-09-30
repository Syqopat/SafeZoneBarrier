# 🧱 SafeZoneBarrier (Minecraft Spigot / Paper Plugin)

![Status](https://img.shields.io/badge/Durum-%C3%87al%C4%B1%C5%9F%C4%B1yor%20%2F%20Working-brightgreen?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge)
![Paper](https://img.shields.io/badge/Minecraft-1.20%2B-blue?style=for-the-badge)
![CI](https://img.shields.io/badge/CI%2FCD-Active-success?style=for-the-badge)

**SafeZoneBarrier**, Minecraft sunucuları (Spigot / Paper) için geliştirilmiş, güvenli bölge sınırlarını görsel parçacıklar (particle effect) ve engelleme duvarları ile koruyan yüksek performanslı bir eklentidir.

---

## 📌 Proje Durumu (Project Status)

- **Durum:** 🟢 **Çalışıyor (Working / Stable)**
- **Test & CI/CD:** GitHub Actions Maven derleme otomasyonu aktif.
- **Entegrasyonlar:** WorldGuard ve CombatLogX kancaları (hooks) mevcut.

---

## 🚀 Özellikler

- **Görsel Sınır Bloklama:** Güvenli bölgeye girmeye veya çıkmaya çalışan oyunculara bariyer parçacıkları gösterir.
- **Savaş Durumu Kontrolü:** CombatLogX kancası sayesinde savaş halindeki oyuncuların güvenli bölgeye kaçmasını engeller.
- **Yapılandırılabilir Mesaj ve Ayarlar:** `src/main/resources/config.yml` üzerinden mesajlar, cooldown ve efekt türleri değiştirilebilir.

---

## 🛠️ Derleme ve Kurulum

```bash
mvn clean package
```
Oluşan `.jar` dosyasını sunucunuzun `plugins` klasörüne ekleyin.

---

## 📄 Lisans

MIT License
