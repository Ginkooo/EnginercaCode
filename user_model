    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private Long id;

    @NotNull
    @Column(name = "user_name", unique = true)
    @Size(max = ValidationConstants.USER_USERNAME_MAXSIZE)
    private String userName;

    @NotNull
    @Size(max = ValidationConstants.USER_PASSWORD_MAXSIZE)
    private String password;

    @NotNull
    @Size(max = ValidationConstants.USER_MAIL_MAXSIZE)
    @Column(unique = true)
    private String mail;

    @NotNull
    @Column(name = "first_name")
    @Size(max = ValidationConstants.USER_FIRSTNAME_MAXSIZE)
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    @Size(max = ValidationConstants.USER_LASTNAME_MAXSIZE)
    private String lastName;

    @Size(max = ValidationConstants.USER_PHONE_MAXSIZE)
    @Pattern(regexp = ValidationConstants.USER_PHONE_REGEX)
    private String phone;

    @Column(name = "second_name")
    private String secondName;

    @NotNull
    @Column(name = "is_admin")
    private boolean admin;
