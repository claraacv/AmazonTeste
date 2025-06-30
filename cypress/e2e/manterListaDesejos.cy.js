describe('manter lista de desejos', () => {
  before(() => {
    cy.visit('https://www.amazon.com.br/');
    cy.get('#nav-link-accountList').click();
    cy.get('input[name="email"]').type('testesudesc@gmail.com');
    cy.get('#continue').click();
    cy.get('input[name="password"]').type('TestesUDESC3!', { log: false });
    cy.get('#signInSubmit').click();
    cy.get('#nav-link-accountList').should('contain.text', 'Olá');
  });

  it('add produto à lista', () => {
    // cy.get('#nav-link-accountList.nav-div').click();
    // cy.get('.a-box.ya-card-rich').click();
    // cy.get('input.a-button-input').click();
    // cy.get('input.a-button-input.a-declarative').click();
    // cy.get('input.a-button-input');
    //#CardInstancemlqQgpXH2ge91JACEvTEcw > .a-cardui-body > .a-spacing-base > ._quad-multi-asin-card-v2_style_topLeftQuadrant__yF7Ht > .a-spacing-none > .a-image-container > img - MALIBU
    // cy.get('.nav.search-field').type('Malibu Renasce{enter}');
    cy.visit('https://www.amazon.com.br/Malibu-renasce-Taylor-Jenkins-Reid/dp/8584392165');
    cy.get('#wishListMainButton > .a-button-inner > .a-button-text').click();
    cy.get('#huc-view-your-list-button > .a-button-inner > .a-button-text').click();

    cy.contains('Malibu Renasce').should('exist');
  });

  it('remover produto da lista', () => {
    cy.get('#delete-button-I37B4LCD659P8C > .a-button-inner > .a-button-input').click();

    cy.contains('Malibu Renasce').should('not.exist');
  });
});