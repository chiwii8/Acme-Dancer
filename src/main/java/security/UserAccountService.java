package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class UserAccountService {

    // Managed repository -----------------------------------------------------

    private UserAccountRepository userAccountRepository;

    // Supporting services ----------------------------------------------------

    // Constructors -----------------------------------------------------------

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    // Simple CRUD methods ----------------------------------------------------

    public UserAccount findByActor(final domain.actores.Actor actor) {
        Assert.notNull(actor);

        UserAccount result;

        result = this.userAccountRepository.findByActorId(actor.getId());

        return result;
    }

    // Other business methods -------------------------------------------------

}
