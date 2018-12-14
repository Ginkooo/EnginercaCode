    @Id
    @GeneratedValue
    @Column(name = "id_visit")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctor")
    @JsonBackReference
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    @JsonBackReference
    private Patient patient;

    @Column(name = "visit_time")
    private Date visitTime;

    @Column
    private String notes;

    @NotNull
    @Column(name = "is_realised")
    private boolean realised;
