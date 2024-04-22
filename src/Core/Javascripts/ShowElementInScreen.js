var element = arguments[0];
var continueMoving = true;
do
{
    if (IsVisible(element, CalculateHeaderHeight(), CalculateFooterHeight()))
    {
        continueMoving = false;
    }
    else
    {
        if (IsInModal(element))
        {
            element.scrollIntoView();
        }
        else
        {
            window.scroll(0, CalculatePosition(element) - CalculateHeaderHeight() - 10);
        }
    }
}while (continueMoving)


function CalculatePosition(element)
{
    var position = 0;
    if (element.offsetParent)
    {
        do
        {
            position += element.offsetTop;
        } while (element = element.offsetParent);
    }
    return [position];
}


function IsInFixedZone(element)
{
    var inFixedZone = false;
    var keepLooking = true;
    var elementParent = element;
    do
    {
        elementParent = elementParent.parentElement;
        if (!elementParent)
        {
            keepLooking = false;
        }
        else
        {
            if (elementParent.tagName.toLowerCase == 'header' || elementParent.classList.contains('footer-template'))
            {
                inFixedZone = true;
                keepLooking = false;
            }
        }
    } while (keepLooking)
    return inFixedZone;
}


function IsInModal(element)
{
    var inModal = false;
    var keepLooking = true;
    var elementParent = element;
    do
    {
        elementParent = elementParent.parentElement;
        if (!elementParent)
        {
            keepLooking = false;
        }
        else
        {
            if (elementParent.classList.contains('modal-content'))
            {
                inModal = true;
                keepLooking = false;
            }
        }
    } while (keepLooking)
    return inModal;
}


function IsVisible(element)
{
    if (IsInFixedZone(element)) return true;
    var areaMinTop = 0;
    var areaMaxDown = window.innerHeight;
    if (IsInModal(element) == false)
    {
        areaMinTop = CalculateHeaderHeight();
        areaMaxDown = window.innerHeight - CalculateFooterHeight();
    }
    var position = element.getBoundingClientRect();
    return ((position.bottom < areaMaxDown && position.bottom > areaMinTop) && (position.top > areaMinTop && position.top < areaMaxDown));
}


function CalculateHeaderHeight()
{
    var header = document.getElementsByTagName('header');
    var headerHeight;
    if (!header || header.length == 0)
    {
        headerHeight = 0;
    }
    else
    {
        if (header[0]) headerHeight = header[0].clientHeight;
        if (!headerHeight) headerHeight = 0;
    }
    return headerHeight;
}


function CalculateFooterHeight()
{
    var footerHeight;
    var footers = document.getElementsByClassName('footer-template');
    if (!footers || footers.length == 0)
    {
        footerHeight = 0;
    }
    else
    {
        var footer = footers[footers.length - 1];
        if (footer) footerHeight = footer.clientHeight;
        if (!footerHeight) footerHeight = 0;
    }
    return footerHeight;
}
