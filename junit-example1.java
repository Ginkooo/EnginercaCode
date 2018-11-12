public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleRepository roleRepository;

    Role role1 = null;
    Role role2 = null;
    Role role3 = null;
    List<Role> roles = null;
    String exampleRoleJson = null;

    @Before
    public void setUp() {
        roles = new ArrayList<>();
        role1 = new Role("Recepcjonistka");
        role2 = new Role("Sprzataczka");
        role3 = new Role("Pielengniarka");
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
    }

    @Test
    public void getRolesTest() throws Exception {
        given(roleRepository.findAll(PageRequest.of(0, 3))).
                willReturn(new PageImpl<>(roles, PageRequest.of(0, 3), roles.size()));
        mockMvc.perform(get("/api/role?page=0&size=3").with(user("admin1")
                .roles("ADMIN")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Recepcjonistka")))
                .andExpect(jsonPath("$[1].name", is("Sprzataczka")))
                .andExpect(jsonPath("$[2].name", is("Pielengniarka")));
    }