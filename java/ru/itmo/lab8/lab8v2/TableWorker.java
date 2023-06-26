package ru.itmo.lab8.lab8v2;



import ru.itmo.lab8.lab8v2.worker.Worker;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TableWorker {
    private Integer id;

    private String owner ;


    private String name ;

    private Double coordinatesX ;

    private Double coordinatesY ;

    private LocalDate creationDate ;

    private Integer salary ;

    private LocalDate startDate;

    private LocalDateTime endDate ;

    private String position ;

    private String organizationFullName;

    private Float organizationAnnualTurnover ;

    private Long organizationEmployeesCount ;

    private String organizationAddressStreet ;

    private String organizationAddressZipCode ;

    private String organizationAddressTown ;

    private Integer organziationAddressLocationX ;

    private Long organziationAddressLocationY ;

    private Integer organziationAddressLocationZ ;


    public TableWorker(){
    }

    public TableWorker(Integer id, String owner, String name, Double coordinatesX, Double coordinatesY, LocalDate creationDate, Integer salary, LocalDate startDate, LocalDateTime endDate, String position, String organizationFullName, Float organizationAnnualTurnover, Long organizationEmployeesCount, String organizationAddressStreet, String organizationAddressZipCode, String organizationAddressTown, Integer organziationAddressLocationX, Long organziationAddressLocationY, Integer organziationAddressLocationZ) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
        this.creationDate = creationDate;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.organizationFullName = organizationFullName;
        this.organizationAnnualTurnover = organizationAnnualTurnover;
        this.organizationEmployeesCount = organizationEmployeesCount;
        this.organizationAddressStreet = organizationAddressStreet;
        this.organizationAddressZipCode = organizationAddressZipCode;
        this.organizationAddressTown = organizationAddressTown;
        this.organziationAddressLocationX = organziationAddressLocationX;
        this.organziationAddressLocationY = organziationAddressLocationY;
        this.organziationAddressLocationZ = organziationAddressLocationZ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCoordinatesX() {
        return coordinatesX;
    }

    public void setCoordinatesX(Double coordinatesX) {
        this.coordinatesX = coordinatesX;
    }

    public Double getCoordinatesY() {
        return coordinatesY;
    }

    public void setCoordinatesY(Double coordinatesY) {
        this.coordinatesY = coordinatesY;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrganizationFullName() {
        return organizationFullName;
    }

    public void setOrganizationFullName(String organizationFullName) {
        this.organizationFullName = organizationFullName;
    }

    public Float getOrganizationAnnualTurnover() {
        return organizationAnnualTurnover;
    }

    public void setOrganizationAnnualTurnover(Float organizationAnnualTurnover) {
        this.organizationAnnualTurnover = organizationAnnualTurnover;
    }

    public Long getOrganizationEmployeesCount() {
        return organizationEmployeesCount;
    }

    public void setOrganizationEmployeesCount(Long organizationEmployeesCount) {
        this.organizationEmployeesCount = organizationEmployeesCount;
    }

    public String getOrganizationAddressStreet() {
        return organizationAddressStreet;
    }

    public void setOrganizationAddressStreet(String organizationAddressStreet) {
        this.organizationAddressStreet = organizationAddressStreet;
    }

    public String getOrganizationAddressZipCode() {
        return organizationAddressZipCode;
    }

    public void setOrganizationAddressZipCode(String organizationAddressZipCode) {
        this.organizationAddressZipCode = organizationAddressZipCode;
    }

    public String getOrganizationAddressTown() {
        return organizationAddressTown;
    }

    public void setOrganizationAddressTown(String organizationAddressTown) {
        this.organizationAddressTown = organizationAddressTown;
    }

    public Integer getOrganziationAddressLocationX() {
        return organziationAddressLocationX;
    }

    public void setOrganziationAddressLocationX(Integer organziationAddressLocationX) {
        this.organziationAddressLocationX = organziationAddressLocationX;
    }

    public Long getOrganziationAddressLocationY() {
        return organziationAddressLocationY;
    }

    public void setOrganziationAddressLocationY(Long organziationAddressLocationY) {
        this.organziationAddressLocationY = organziationAddressLocationY;
    }

    public Integer getOrganziationAddressLocationZ() {
        return organziationAddressLocationZ;
    }

    public void setOrganziationAddressLocationZ(Integer organziationAddressLocationZ) {
        this.organziationAddressLocationZ = organziationAddressLocationZ;
    }
}
