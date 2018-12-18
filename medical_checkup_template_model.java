    @Id
    @GeneratedValue
    @Column(name = "id_medical_checkup_template")
    private Long id;

    @NotNull
    @Size(max = ValidationConstants.MEDICAL_CHECKUP_TEMPLATE_NAME_SIZE)
    private String name;

    @NotNull
    @Size(max = ValidationConstants.MEDICAL_CHECKUP_TEMPLATE_DESCRIPTION_SIZE)
    private String description;

    @NotNull
    @Column(name = "is_intepretation_required")
    private boolean isIntepretationRequired;

    @NotNull
    private Double price;
