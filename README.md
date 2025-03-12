# DUAN1_PH27232

Chào mừng bạn đến với **DUAN1_PH27232**, một ứng dụng Android (Java) phục vụ quản lý và mua bán (E-commerce) các sản phẩm Laptop/PC. Dự án sử dụng Android SDK và các thư viện giao diện hiện đại nhằm mang lại trải nghiệm tốt nhất cho người dùng.

---

## Mục lục
1. [Giới thiệu](#gioithieu)  
2. [Cài đặt](#cai-dat)  
3. [Thư viện phụ thuộc](#thu-vien-phu-thuoc)  
4. [Cấu hình Gradle](#cau-hinh-gradle)  
5. [Tác giả](#tac-gia)  

---

<a name="gioithieu"></a>
## 1. Giới thiệu

**DUAN1_PH27232** là đồ án trong môn **Lập trình di động**, tập trung vào phát triển ứng dụng mua bán Laptop/PC trên nền tảng Android. Ứng dụng cung cấp:

- **Danh mục sản phẩm** (Laptop, PC, phụ kiện, …).  
- **Tìm kiếm, lọc sản phẩm** theo nhiều tiêu chí.  
- **Giỏ hàng, thanh toán đơn giản (offline)**.  
- **Quản lý người dùng** với quyền **Admin** và **Nhân viên**.  
- **Quản lý khách hàng**, hóa đơn, khuyến mãi, thống kê doanh thu cơ bản.  
- **Giao diện** dựa trên Material Design, hỗ trợ Navigation Drawer, v.v.

Với việc sử dụng Java làm ngôn ngữ chính, kết hợp nhiều thư viện, nhóm chúng tôi mong muốn mang đến một ứng dụng mẫu trực quan, dễ mở rộng và dễ bảo trì.

---

<a name="cai-dat"></a>
## 2. Cài đặt

Để cài đặt và chạy ứng dụng trên thiết bị Android hoặc máy ảo (AVD), bạn cần:

1. **Sao chép (clone) dự án**  
   ```bash
   git clone https://github.com/buiminhnhut114/DuAn1_PH27232.git
   ```

2. **Mở dự án bằng Android Studio**  
   - Khuyến nghị sử dụng **Android Studio Chipmunk (2021.2.1)** hoặc phiên bản mới hơn.

3. **Đồng bộ (build) dự án**  
   - Chờ Android Studio tải và cấu hình thư viện phụ thuộc.  
   - Kiểm tra **minSdk = 21**, **targetSdk = 32**, **compileSdk = 32**.  

4. **Kết nối thiết bị Android**  
   - Bật Developer Options và USB Debugging trên điện thoại, hoặc  
   - Sử dụng **AVD** (Android Virtual Device) ngay trong Android Studio.

5. **Chạy ứng dụng**  
   - Nhấn nút **Run** hoặc **Shift + F10** để cài đặt và chạy app trên thiết bị/máy ảo.

---

<a name="thu-vien-phu-thuoc"></a>
## 3. Thư viện phụ thuộc

Dự án sử dụng một số thư viện chính như sau:

- **AndroidX AppCompat**: Giúp ứng dụng tương thích nhiều phiên bản Android.  
- **Google Material Components**: Áp dụng Material Design cho giao diện.  
- **ConstraintLayout**: Tối ưu bố cục giao diện.  
- **CircleIndicator**: Tạo indicator dạng tròn cho ViewPager, Slider…  
- **CircleImageView**: Tạo ảnh đại diện hình tròn.  
- **JUnit** / **Espresso**: Hỗ trợ kiểm thử.

---
## 4. DEMO
![IMG_2426](https://github.com/user-attachments/assets/78b63587-2ac1-4abd-bf79-9f0ad695b368)
![IMG_2425](https://github.com/user-attachments/assets/12ff91cb-ebfe-43bd-9a43-532354a1af76)
![IMG_2424](https://github.com/user-attachments/assets/e051346a-0441-436b-ad42-8f7b5a1f5f79)
![IMG_2423](https://github.com/user-attachments/assets/ac6a8915-7ace-4103-948f-7427e3e55a91)
![IMG_2422](https://github.com/user-attachments/assets/bcdf6fea-edb6-48e5-9361-64c5f2e350c2)
![IMG_2421](https://github.com/user-attachments/assets/4e5df2db-9b8f-482c-a6fb-114cae3feaf5)
![IMG_2420](https://github.com/user-attachments/assets/701b1034-7d96-485d-82e7-b581b4abc1fe)
![IMG_2419](https://github.com/user-attachments/assets/c4dddf26-664e-4e33-a292-01bb18de9e1e)
![IMG_2418](https://github.com/user-attachments/assets/80ca8a4c-5276-4eb0-8e16-dc408e64b243)
![IMG_2417](https://github.com/user-attachments/assets/1611d30b-794d-4ad2-a200-3a15abf98053)


<a name="cau-hinh-gradle"></a>
## 5. Cấu hình Gradle

Dưới đây là một số thông tin quan trọng về cấu hình Gradle của dự án.

### **settings.gradle**
```groovy
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "DuAn1_PH27232"
include ':app'
```

### **build.gradle (Project)**
```groovy
plugins {
    id 'com.android.application' version '7.3.1' apply false
    id 'com.android.library' version '7.3.1' apply false
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```

### **build.gradle (Module)**
```groovy
plugins {
    id 'com.android.application'
}

android {
    compileSdk 32

    defaultConfig {
        applicationId "com.example.duan1_ph27232"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    namespace 'com.example.duan1_ph27232'
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.5.1'
    implementation 'com.google.android.material:material:1.7.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    implementation 'me.relex:circleindicator:2.1.6'
    implementation 'de.hdodenhof:circleimageview:3.1.0'
}
```

### **gradle-wrapper.properties**
```txt
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https://services.gradle.org/distributions/gradle-8.9-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

---

<a name="tac-gia"></a>
## 5. Tác giả

Dự án **DUAN1_PH27232** được thực hiện bởi:

| Tên thành viên         | GitHub                           | Vai trò trong dự án        |
|------------------------|--------------------------------|----------------------------|
| Bùi Minh Nhựt         | [buiminhnhut114](https://github.com/buiminhnhut114) | Quản lý dự án, backend |
| Đỗ Minh Chương        | [dominhchuong](https://github.com/dominhchuong) | Xây dựng UI/UX         |
| Lê Nguyễn Xuân Doãn   | [lenguyenxuandoan](https://github.com/lenguyenxuandoan) | Kiểm thử & báo cáo     |

Nếu có bất kỳ góp ý hay thắc mắc nào, vui lòng mở **Issue** trên GitHub hoặc liên hệ qua email.

---

### **Lời cuối**
Cảm ơn bạn đã quan tâm đến **DUAN1_PH27232**!  
Nếu bạn gặp bất kỳ khó khăn nào trong quá trình cài đặt hoặc sử dụng, hãy liên hệ với chúng tôi để được hỗ trợ.  
Chúc bạn một ngày tốt lành! 🎉

<!-- End of README -->
