    @Id
    @GeneratedValue
    @Column(name = "id_medical_checkup")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medical_checkup_template")
    @JsonBackReference
    private MedicalCheckupTemplate medicalCheckupTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    @JsonBackReference
    private Patient patient;

    private Date date;

    @Size(max = ValidationConstants.MEDICAL_CHECKUP_RESULT_FILE_SIZE)
    @Column(name = "result_file")
    private String resultFile;

    @Size(max = ValidationConstants.MEDICAL_CHECKUP_RESULT_INTERPRETATION_SIZE)
    @Column(name = "result_interpretation")
    private String resultInterpretation;

    @NotNull
    @Column(name = "is_completed")
    private boolean completed;
