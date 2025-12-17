#  University Timetable Scheduler - Complete Guide

Based on your folder structure, here's the comprehensive README for your project:

---

#  University Timetable Scheduler

A modern JavaFX-based application for managing university resources, generating timetables, and optimizing room allocations with an intuitive admin dashboard.

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-21-blue)
![License](https://img.shields.io/badge/License-CC%20BY--NC%204.0-green)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow)

##  **Current Project Structure**
```
TIME-TABLE-SCHEDULER/
├── vscode/
│   ├── launch.json              # VS Code launch configurations
│   └── settings.json           # VS Code workspace settings
├── Controller/
│   ├── DashboardController.java          # Main dashboard controller
│   ├── DashboardHomeController.java      # Dashboard home controller
│   ├── GenerateTimetableController.java  # Timetable generation
│   ├── LoginController.java              # Authentication controller
│   └── RoomsLabsController.java          # Rooms management controller
├── DAO/
│   ├── Database/               # Database configuration (to be added)
│   └── Model/
│       ├── Admin.java          # Admin user model
│       └── RoomLab.java        # Room/Lab entity model
├── Service/                    # Business logic layer (to be added)
├── UI/
│   ├── dashboard_home.fxml     # Dashboard homepage UI
│   ├── dashboard.css           # Dashboard styling
│   ├── dashboard.fxml          # Main dashboard layout
│   ├── generate.timetable.fxml # Timetable generator UI
│   ├── login.css              # Login page styling
│   ├── login.fxml             # Login page UI
│   ├── rooms_labs.css         # Rooms management styling
│   └── rooms_labs.fxml        # Rooms management UI
├── Main.java                   # Application entry point
└── README.md                   # This documentation file
```

##  **Features Implemented**

###  **Completed Features**
1. **Secure Login System**
   - Admin authentication (`admin/1234`)
   - Animated particle background
   - Real-time UI feedback

2. **Modern Dashboard**
   - Neon cyber-themed interface
   - Animated gradient backgrounds
   - Glass-morphism UI components
   - Real-time statistics display

3. **Rooms & Labs Management**
   - CRUD operations for rooms/labs
   - Department-wise categorization
   - Capacity tracking
   - Status management (Available/Occupied)

4. **Timetable Generation**
   - Multiple algorithm selection
   - Basic timetable generation
   - Output display in text area

###  **In Progress**
- Database integration
- Student management module
- Advanced timetable algorithms
- Export functionality

##  **Getting Started**

### **Prerequisites**
- **Java JDK 17+** ([Download OpenJDK](https://adoptium.net/))
- **JavaFX SDK 21.0.1** ([Download here](https://gluonhq.com/products/javafx/))
- **Visual Studio Code** with Java Extension Pack

### **Installation Steps**

#### **Step 1: Download JavaFX SDK**
1. Download JavaFX SDK from [Gluon HQ](https://gluonhq.com/products/javafx/)
2. Extract to `C:/javafx-sdk-21.0.1/` (Windows) or `/opt/javafx-sdk-21.0.1/` (Linux/Mac)

#### **Step 2: Configure VS Code**
Your `vscode/settings.json` is already configured with:
```json
{
    "java.project.referencedLibraries": [
        "lib/**/*.jar",
        "d:/javafx-sdk-24.0.1/lib/*.jar"
    ]
}
```

#### **Step 3: Update JavaFX Path**
Edit `vscode/settings.json` to match your JavaFX installation path:
```json
{
    "java.project.referencedLibraries": [
        "lib/**/*.jar",
        "C:/javafx-sdk-21.0.1/lib/*.jar"  // Update this path
    ]
}
```

#### **Step 4: Configure Launch Settings**
Your `vscode/launch.json` already contains:
```json
{
    "vmArgs": "--module-path D:/javafx-sdk-24.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.media"
}
```
Update the path to match your installation:
```json
{
    "vmArgs": "--module-path C:/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.graphics"
}
```

### **Step 5: Run the Application**
1. Open the project in VS Code
2. Open `Main.java`
3. Press `F5` or click "Run and Debug"
4. Select "Launch Main" configuration

##  **Default Login Credentials**
```
Username: admin
Password: 1234
```

##  **UI Components**

### **Login Page**
- Location: `UI/login.fxml`
- Style: `UI/login.css`
- Features:
  - Animated gradient background
  - Particle system
  - Glass-morphism card design
  - Responsive layout

### **Dashboard**
- Location: `UI/dashboard.fxml`
- Style: `UI/dashboard.css`
- Features:
  - Sidebar navigation
  - Dynamic content loading
  - Neon theme with glow effects
  - Animated statistics cards

### **Rooms & Labs Management**
- Location: `UI/rooms_labs.fxml`
- Style: `UI/rooms_labs.css`
- Features:
  - Tab-based interface
  - Real-time statistics
  - Form validation
  - Table views for data display

### **Timetable Generator**
- Location: `UI/generate.timetable.fxml`
- Features:
  - Algorithm selection dropdown
  - Generation output area
  - Multiple scheduling strategies

##  **Data Models**

### **Admin Model** (`DAO/Model/Admin.java`)
```java
public class Admin {
    private int id;
    private String username;
    private String passwordHash;
    // Getters and setters
}
```

### **RoomLab Model** (`DAO/Model/RoomLab.java`)
```java
public class RoomLab {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty type; // "ROOM" or "LAB"
    private IntegerProperty capacity;
    private StringProperty status; // "AVAILABLE" or "OCCUPIED"
    // Property getters for JavaFX binding
}
```

## **Controller Architecture**

### **LoginController** (`Controller/LoginController.java`)
- Handles user authentication
- Manages animated background
- Navigates to dashboard

### **DashboardController** (`Controller/DashboardController.java`)
- Main application controller
- Manages sidebar navigation
- Loads dynamic content
- Handles logout functionality

### **RoomsLabsController** (`Controller/RoomsLabsController.java`)
- Manages room and lab CRUD operations
- Handles form validation
- Updates statistics in real-time

### **GenerateTimetableController** (`Controller/GenerateTimetableController.java`)
- Manages timetable generation
- Implements algorithm selection
- Displays generation results

## **Navigation Flow**
```
Login Page → Dashboard → [Select Module]
    ├── Dashboard Home (Statistics)
    ├── Rooms & Labs Management
    ├── Students (Future Implementation)
    ├── Generate Timetable
    └── Settings (Future Implementation)
```

## **Development Guidelines**

### **Adding New Features**
1. Create FXML file in `UI/` folder
2. Create corresponding CSS file (optional)
3. Create controller in `Controller/` folder
4. Update `DashboardController.java` to load new page
5. Add navigation button in `dashboard.fxml`

### **Code Structure**
```java
// Controller Naming Convention
[FeatureName]Controller.java

// FXML Naming Convention
[feature_name].fxml

// CSS Naming Convention
[feature_name].css
```

### **JavaFX Properties**
Use JavaFX properties for data binding:
```java
private StringProperty name = new SimpleStringProperty();

// Getter for JavaFX binding
public StringProperty nameProperty() {
    return name;
}
```

## **Dependencies**

### **Required Libraries**
1. **JavaFX SDK** - UI framework
2. **MySQL Connector** (Future) - Database connectivity
3. **Jackson** (Future) - JSON processing

### **VS Code Extensions**
```json
{
    "recommendations": [
        "redhat.java",
        "vscjava.vscode-java-pack",
        "vscjava.vscode-java-debug",
        "vscjava.vscode-java-dependency",
        "angryobject.fxml-support"
    ]
}
```

## **Troubleshooting**

### **Common Issues**

#### **Issue: "JavaFX runtime components are missing"**
**Solution:** Update `launch.json` with correct JavaFX path:
```json
"vmArgs": "--module-path C:/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml"
```

#### **Issue: FXML files not loading**
**Solution:** Ensure correct path in controller:
```java
FXMLLoader.load(getClass().getResource("/UI/filename.fxml"));
```

#### **Issue: CSS styles not applied**
**Solution:** Check CSS path in FXML:
```xml
stylesheets="@dashboard.css"
```

### **Debug Mode**
1. Set breakpoints in VS Code
2. Press `F5` to start debugging
3. Use Debug Console for variable inspection

## **Future Enhancements**

### **Phase 1 (Current)**
-  Login system
-  Dashboard UI
-  Rooms management
-  Basic timetable generation

### **Phase 2 (Next)**
- Database integration (MySQL/SQLite)
- Student management module
- Advanced timetable algorithms
- Export to PDF/Excel

### **Phase 3 (Future)**
- Mobile app companion
- Real-time notifications
- AI-powered scheduling
- Cloud deployment

## **Contributing**

### **For Team Members**
1. Clone the repository
2. Install prerequisites
3. Run `Main.java` to verify setup
4. Create feature branch: `feature/your-feature-name`
5. Submit pull request

### **Coding Standards**
- Use JavaFX properties for data binding
- Follow MVC pattern
- Add comments for complex logic
- Test features before committing

## **Learning Resources**

### **JavaFX Documentation**
- [JavaFX Official Docs](https://openjfx.io/)
- [JavaFX Tutorial](https://code.makery.ch/library/javafx-tutorial/)
- [JavaFX CSS Reference](https://openjfx.io/javadoc/21/javafx.graphics/javafx/scene/doc-files/cssref.html)

### **VS Code Java**
- [VS Code Java Guide](https://code.visualstudio.com/docs/java/java-tutorial)
- [JavaFX in VS Code](https://code.visualstudio.com/docs/java/java-javafx)

## **License**

This project is licensed under the Creative Commons Attribution-NonCommercial 4.0 International License.

You are free to:

Share, copy, and redistribute the material
Adapt, remix, transform, and build upon the material
Use for academic, educational, and research purposes
Under these terms:

Attribution Required: Credit must be given to the authors
NonCommercial: Commercial use is not permitted
For complete license terms, see the LICENSE file.

## **Team Members**

- [Huzaifa Ashrafi] - Project Lead & Developer
- [Muhammad Affan] - UI/UX Designer
- [Muhammad Ezaz Azhar] - UI/UX Designer & Backend Developer
- [Ammar Khan] - Database Administrator

## **Support**

For support or questions:
- Email: Huzaifa123ashrafi@gmail.com
- Issue Tracker: [GitHub Issues](https://github.com/HuzaifaAshrafi1/time-table-scheduler/issues)

---

**Happy Scheduling!** 📅