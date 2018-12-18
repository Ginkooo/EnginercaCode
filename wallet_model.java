    @Id
    @GeneratedValue
    @Column(name = "id_wallet")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    @JsonBackReference
    private Patient patient;

    private Double amount;
