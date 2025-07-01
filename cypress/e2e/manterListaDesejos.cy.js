describe('manter lista de desejos', () => {
  beforeEach(() => {
    cy.visit('https://www.amazon.com.br/');
    cy.get('#nav-link-accountList').click();
    cy.get('input[name="email"]').type('testesudesc@gmail.com');
    cy.get('#continue').click();
    cy.get('input[name="password"]').type('TestesUDESC3!', { log: false });
    cy.get('#signInSubmit').click();
    cy.get('#nav-link-accountList').should('contain.text', 'Olá');
  });

  beforeEach(() => {
    Cypress.on('uncaught:exception', (err, runnable) => {
      return false;
    });
  })

  it('add produto à lista', () => {
    cy.visit('https://www.amazon.com.br/Malibu-renasce-Taylor-Jenkins-Reid/dp/8584392165');
    cy.get('#add-to-wishlist-button-submit').should('be.visible').click();
    cy.get('a.a-button-text').contains('Ver sua lista').click();

    cy.contains('Malibu renasce').should('exist');
  });

  it('remover produto da lista', () => {
    cy.visit('https://www.amazon.com.br/hz/wishlist/ls');
    cy.get('input.a-button-input.a-declarative').click();

    cy.contains('Malibu Renasce').should('not.exist');
    cy.contains('Removido').should('exist');
  });
});