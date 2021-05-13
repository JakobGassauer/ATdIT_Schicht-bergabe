package library.persistence.implementation;

import library.model.implementation.*;
import library.persistence.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseFactory {
    DatabaseService service;

    //maps types from db service to types used in the model (?)
    // no direct database access/queries

    //Data fromD DB
    public final ArrayList<ResidentData> residentsData;
    public final ArrayList<IncidentData> incidentsData;
    public final ArrayList<ShiftScheduleData> shiftSchedulesData;
    public final ArrayList<EmployeeData> employeesData;
    public final ArrayList<ICEData> icesData;
    public final ArrayList<MedicationData> medicationsData;
    public final ArrayList<MedPlanData> medPlansData;
    public final ArrayList<StationData> stationsData;
    public final ArrayList<VisitsData> visitsData;

//Namenskonventionen! Lists to be used in gui
    public final ArrayList<Resident> residents = new ArrayList<>();
    public final ArrayList<Incident> incidents = new ArrayList<>();
    public final ArrayList<ShiftSchedule> shiftSchedules = new ArrayList<>();
    public final ArrayList<Employee> employees = new ArrayList<>();
    public final ArrayList<ICE> ices = new ArrayList<>();
    public final ArrayList<Medication> medications = new ArrayList<>();
    public final ArrayList<MedPlan> medPlans = new ArrayList<>();
    public final ArrayList<Station> stations = new ArrayList<>();
    public final ArrayList<Visits> visits = new ArrayList<>();




    public DatabaseFactory(){
        service = new DatabaseService();

        residentsData = service.getResidentData();
        incidentsData = service.getIncidentData();
        shiftSchedulesData = service.getShiftScheduleData();
        employeesData = service.getEmployeeData();
        icesData = service.getICEData();
        medicationsData = service.getMedicationData();
        medPlansData = service.getMedPlanData();
        stationsData = service.getStationData();
        visitsData = service.getVisitData();

        convertToModelObjects();
    }

    private void convertToModelObjects() {
        for(ResidentData entities : residentsData){
            Resident entity = new Resident(entities.resID,
                    entities.name,
                    entities.surname,
                    entities.age,
                    entities.stationID,
                    entities.room) ;
            residents.add(entity);
        }

        for(IncidentData entities : incidentsData){
            Incident entity = new Incident(entities.incidentID,
                    entities.resID,
                    entities.shiftID,
                    entities.description,
                    entities.incidentsDate) ;
            incidents.add(entity);
        }

        for(ShiftScheduleData entities : shiftSchedulesData){
            ShiftSchedule entity = new ShiftSchedule(entities.shiftID,
                    entities.employeeID,
                    entities.category,
                    entities.date,
                    entities.shiftIncidents) ;
            shiftSchedules.add(entity);
        }

        for(EmployeeData entities : employeesData){
            Employee entity = new Employee(entities.employeeID,
                    entities.name,
                    entities.surname,
                    entities.age,
                    entities.stationID) ;
            employees.add(entity);
        }

        for(ICEData entities : icesData){
            ICE entity = new ICE(entities.iceID,
                    entities.resID,
                    entities.name,
                    entities.surname,
                    entities.telnumber,
                    entities.adress) ;
            ices.add(entity);
        }

        for(MedicationData entities : medicationsData){
            Medication entity = new Medication(entities.medicID,
                    entities.name) ;
            medications.add(entity);
        }

        for(MedPlanData entities : medPlansData){
            MedPlan entity = new MedPlan(entities.medID,
                    entities.resID,
                    entities.concentration,
                    entities.intakeFrequency,
                    entities.medicID) ;
            medPlans.add(entity);
        }

        for(StationData entities : stationsData){
            Station entity = new Station(entities.stationID,
                    entities.name) ;
            stations.add(entity);
        }

        for(VisitsData entities : visitsData){
            Visits entity = new Visits(entities.visitID,
                    entities.description,
                    entities.resID) ;
            visits.add(entity);
        }

    }


    public Resident getSingleResident(int index) {
        return residents.get(index);
    }

    public Resident getSingleResident(String name) {
       ResidentData residentData = service.getSingleResidentData(name);
       return new Resident(residentData.resID,residentData.name,
               residentData.surname,residentData.age,
               residentData.stationID,residentData.room);
    }

    public ShiftSchedule getSingleShiftSchedule(Object category, Date date) {
        ShiftScheduleData shiftScheduleData = service.getSingleShiftScheduleData(category,date);
        return new ShiftSchedule(shiftScheduleData.shiftID,shiftScheduleData.employeeID,
                shiftScheduleData.category,shiftScheduleData.date,
                shiftScheduleData.shiftIncidents);
    }

    public String getSingleVisitDescription(int resID) {
        return service.getSingleVisitDataDescription(resID);
    }


    public ICE getSingleICE(int resID){
        ICEData iceData = service.getSingleICEData(resID);
        return new ICE(iceData.iceID, iceData.resID,iceData.name,iceData.surname,iceData.telnumber,iceData.adress);
    }

    public Incident getSingleIncident(int resID, Date date){
        IncidentData incidentData = service.getSingleIncidentData(resID,date);
        return new Incident(incidentData.incidentID,incidentData.resID,
                incidentData.shiftID,incidentData.description,
                incidentData.incidentsDate);
    }
    public Incident getSingleIncident(int resID){
        IncidentData incidentData = service.getSingleIncidentData(resID);
        return new Incident(incidentData.incidentID,incidentData.resID,
                incidentData.shiftID,incidentData.description,
                incidentData.incidentsDate);
    }

    public String getSingleMedication(int medicID){
        return service.getSingleMedicationData(medicID);
    }

    public MedPlan getSingleMedPlan(int resID){
        MedPlanData medPlanData = service.getSingleMedPlanData(resID);
        return new MedPlan(medPlanData.medID,medPlanData.resID,
                medPlanData.concentration,medPlanData.intakeFrequency,
                medPlanData.medicID);
    }

}
